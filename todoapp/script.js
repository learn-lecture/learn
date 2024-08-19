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
}

function createTodoElement(data) {
    const item = document.createElement('div');
    item.classList.add('item');

    const checkBox = document.createElement('input');
    checkBox.type = 'checkbox';

    if (item.complete) {
        item.className.add('complete');
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

    action.append(edit);
    action.append(remove);

    item.append(checkBox);
    item.append(input);
    item.append(action);

    return { item, input, edit, remove };
}