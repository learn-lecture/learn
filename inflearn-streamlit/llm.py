from langchain_upstage import UpstageEmbeddings
from langchain_pinecone import PineconeVectorStore
from langchain_core.output_parsers import StrOutputParser
from langchain_core.prompts import ChatPromptTemplate
#from langchain.chains import RetrievalQA
from langchain import hub
from langchain_upstage import ChatUpstage

from langchain.chains import create_retrieval_chain
from langchain.chains.combine_documents import create_stuff_documents_chain
from langchain.chains import create_history_aware_retriever
from langchain_core.prompts import MessagesPlaceholder
from langchain_community.chat_message_histories import ChatMessageHistory
from langchain_core.chat_history import BaseChatMessageHistory
from langchain_core.runnables.history import RunnableWithMessageHistory

store = {}


def get_session_history(session_id: str) -> BaseChatMessageHistory:
    if session_id not in store:
        store[session_id] = ChatMessageHistory()
    return store[session_id]

def get_retriever():
    embedding = UpstageEmbeddings(model="solar-embedding-1-large")
    index_name = 'tax-table-index'
    database = PineconeVectorStore.from_existing_index(embedding=embedding, index_name=index_name)

    retriever=database.as_retriever()
    return retriever

def get_llm(model='solar-1-mini-chat'):
    llm = ChatUpstage(model=model)
    return llm

def get_dictionary_chain(llm):
    prompt = ChatPromptTemplate.from_template("""
        1. 사용자의 질문을 보고, 우리의 사전을 참고해서 사용자의 질문을 변경해주세요.
        2. 만약, 변경할 필요가 없다고 판단된다면 변경하지 않아도 됩니다.
        사전: {dictionary}
                                            
        질문: {question}
    """)

    dictionary_chain = prompt | llm | StrOutputParser()
    return dictionary_chain

def get_rag_chain(llm):
    retriever = get_retriever()
    prompt = hub.pull("rlm/rag-prompt")

    #qa_chain = RetrievalQA.from_chain_type(llm,retriever=retriever,chain_type_kwargs={"prompt": prompt})
    contextualize_q_system_prompt = (
        "Given a chat history and the latest user question "
        "which might reference context in the chat history, "
        "formulate a standalone question which can be understood "
        "without the chat history. Do NOT answer the question, "
        "just reformulate it if needed and otherwise return it as is."
    )

    contextualize_q_prompt = ChatPromptTemplate.from_messages(
        [
            ("system", contextualize_q_system_prompt),
            MessagesPlaceholder("chat_history"),
            ("human", "{input}"),
        ]
    )

    history_aware_retriever = create_history_aware_retriever(llm, retriever, contextualize_q_prompt)

    system_prompt = (
        "You are an assistant for question-answering tasks. "
        "Use the following pieces of retrieved context to answer "
        "the question. If you don't know the answer, say that you "
        "don't know. Use three sentences maximum and keep the "
        "answer concise."
        "\n\n"
        "{context}"
    )
    qa_prompt = ChatPromptTemplate.from_messages(
        [
            ("system", system_prompt),
            MessagesPlaceholder("chat_history"),
            ("human", "{input}"),
        ]
    )
    question_answer_chain = create_stuff_documents_chain(llm, qa_prompt)

    rag_chain = create_retrieval_chain(history_aware_retriever, question_answer_chain)

    conversational_rag_chain = RunnableWithMessageHistory(
        rag_chain,
        get_session_history,
        input_messages_key="input",
        history_messages_key="chat_history",
        output_messages_key="answer",
    ).pick('answer')

    return conversational_rag_chain

def get_ai_response(user_message):
    llm = get_llm('solar-pro')
    dictionary_chain = get_dictionary_chain(llm)
    rag_chain = get_rag_chain(llm)
    dictionary = ["사람을 나타내는 표현은 거주자로 정의한다.", "금액과 관련된 내용이면 질문의 끝에 *참고: 제대로 된 연산이 필요합니다.*를 추가한다."]
    
    tax_chain = {"input": dictionary_chain} | rag_chain
    ai_response = tax_chain.stream(
        {"dictionary": dictionary, "question": user_message},
        config={"configurable": {"session_id": "abc123"}},
    )
    
    return ai_response