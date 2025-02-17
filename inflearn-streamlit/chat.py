import streamlit as st    
from langchain_core.output_parsers import StrOutputParser
from langchain_core.prompts import ChatPromptTemplate
from langchain.chains import RetrievalQA
from langchain import hub
from langchain_upstage import ChatUpstage
from dotenv import load_dotenv
from langchain_upstage import UpstageEmbeddings
from langchain_pinecone import PineconeVectorStore

load_dotenv()

st.set_page_config(page_title="소득세 챗봇", page_icon="💰")

st.title("💰 소득세 챗봇")
st.caption("소득세에 관련된 모든 것을 답변해드립니다!")

if 'message_list' not in st.session_state:
    st.session_state.message_list = []

for message in st.session_state.message_list:
    with st.chat_message(message["role"]):
        st.write(message["content"])

def get_ai_message(user_message):
    embedding = UpstageEmbeddings(model="solar-embedding-1-large")
    index_name = 'tax-table-index'
    database = PineconeVectorStore.from_existing_index(embedding=embedding, index_name=index_name)

    llm = ChatUpstage()
    prompt = hub.pull("rlm/rag-prompt")
    retriever=database.as_retriever()

    qa_chain = RetrievalQA.from_chain_type(
        llm,
        retriever=retriever,
        chain_type_kwargs={"prompt": prompt}
    )

    dictionary = ["사람을 나타내는 표현은 거주자로 정의한다.", "금액과 관련된 내용이면 질문의 끝에 *참고: 제대로 된 연산이 필요합니다.*를 추가한다."]

    prompt = ChatPromptTemplate.from_template("""
        1. 사용자의 질문을 보고, 우리의 사전을 참고해서 사용자의 질문을 변경해주세요.
        2. 만약, 변경할 필요가 없다고 판단된다면 변경하지 않아도 됩니다.
        사전: {dictionary}
                                            
        질문: {question}
    """)

    dictionary_chain = prompt | llm | StrOutputParser()
    tax_chain = {"query": dictionary_chain} | qa_chain
    ai_response = tax_chain.invoke({"dictionary": dictionary, "question": user_message})
    return ai_response['result']

if user_question := st.chat_input(placeholder="소득세에 관련된 궁금한 내용들을 말씀해주세요!"):
    with st.chat_message("user"):
        st.write(user_question)
    st.session_state.message_list.append({"role":"user", "content": user_question})

    with st.spinner("답변을 생성하는 중입니다."):
        ai_message = get_ai_message(user_question)
        with st.chat_message("ai"):
            st.write(ai_message)
        st.session_state.message_list.append({"role":"ai", "content": ai_message})

    