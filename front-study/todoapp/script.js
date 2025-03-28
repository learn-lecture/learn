const list = document.querySelector('#list');
const createBtn = document.querySelector('#create-btn');

let todos = [];

createBtn.addEventListener('click', createNewTodo);

function createNewTodo() {
    const items = {
        id: new Date().getTime(),
        text: '',
        complete: false
    }

    todos.unshift(items);
    const { item, input, edit, remove} = createTodoElement(items);
    list.prepend(item);
    input.removeAttribute('disabled');
    input.focus();
    saveLocalStorage();
}

function createTodoElement(data) {
    const item = document.createElement('div');
    item.classList.add('item');

    const checkBox = document.createElement('input');
    checkBox.type = 'checkbox';
    checkBox.checked = data.complete;

    if (data.complete) {
        item.classList.add('complete');
    }

    const input = document.createElement('input');
    input.type = 'text';
    input.value = data.text;
    input.setAttribute('disabled', '');

    const action = document.createElement('div');
    action.classList.add('actions');

    const edit = document.createElement('button');
    edit.classList.add('material-icons');
    edit.innerText = 'edit';

    const remove = document.createElement('button');
    remove.classList.add('material-icons', 'remove-btn');
    remove.innerText = 'remove_cycles';

    input.addEventListener('input', () => {
        data.text = input.value;
        saveLocalStorage();
    })

    input.addEventListener('blur', () => {
        input.setAttribute('disabled', '');
    })

    edit.addEventListener('click', () => {
        if (data.complete) { return; }
        input.removeAttribute('disabled');
        input.focus();
    })

    remove.addEventListener('click', () => {
        todos = todos.filter(t => t.id !== data.id);
        item.remove();
        saveLocalStorage();
    })

    checkBox.addEventListener('change', e => {
        data.complete = checkBox.checked;
        data.complete ? item.classList.add('complete') : item.classList.remove('complete');
        saveLocalStorage();
    })

    action.append(edit);
    action.append(remove);

    item.append(checkBox);
    item.append(input);
    item.append(action);

    return { item, input, edit, remove };
}

function saveLocalStorage() {
    const data = JSON.stringify(todos);
    localStorage.setItem('todos', data);
}

function loadFromLocalStorage() {
    const data = localStorage.getItem('todos');
    if (data) {
        todos = JSON.parse(data);
    }
}

function disPlayTodos() {
    loadFromLocalStorage();
    for (let i = 0; i < todos.length; i++) {
        const todo = todos[i];
        const {item} = createTodoElement(todo);
        list.append(item);
    }
}

disPlayTodos();