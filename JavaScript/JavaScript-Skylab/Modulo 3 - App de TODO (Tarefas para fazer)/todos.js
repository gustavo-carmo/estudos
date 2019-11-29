var listElement = document.querySelector('#app ul');
var inputElement = document.querySelector('#app input');
var buttonElement = document.querySelector('#app button');

var todoList = JSON.parse(localStorage.getItem('list_todos')) || [];

function renderTodos() {

    listElement.innerHTML = '';

    for (todo of todoList) {

        var indexOfTodo = todoList.indexOf(todo);        

        // Cria o elemento Link para a exclusão
        var todoLinkRemoveElement = document.createElement('a');
        var todoLinkRemoveText = document.createTextNode('Excluir');
        todoLinkRemoveElement.setAttribute('href', '#');
        todoLinkRemoveElement.appendChild(todoLinkRemoveText);
        todoLinkRemoveElement.setAttribute('onclick', `deleteTodo(${indexOfTodo})`);
        todoLinkRemoveElement.setAttribute('style', 'position: absolute; margin-right: 10px; right: 0;');
        
        // Cria o elemento Li que é o item da lista e adiciona o link de exclusão
        var todoElement = document.createElement('li');
        var todoText = document.createTextNode(todo);
        todoElement.appendChild(todoText);
        todoElement.appendChild(todoLinkRemoveElement);
        todoElement.setAttribute('class', 'list-group-item');
        todoElement.setAttribute('style', 'margin-top: 5px; border: 1px solid;');

        // Adiciona o elemento Li a Lista
        listElement.appendChild(todoElement);
    }

}

function addTodo () {

    var todoText = inputElement.value;

    todoList.push(todoText);
    inputElement.value = "";
    renderTodos();
    saveTodoStorage();
}

function deleteTodo(pos) {

    todoList.splice(pos, 1);

    renderTodos();
    saveTodoStorage();
}

function saveTodoStorage() {
    localStorage.setItem('list_todos', JSON.stringify(todoList));
}

buttonElement.onclick = addTodo;

renderTodos();

/*
function addItemOnTodoList() {
    var listElement = document.querySelector('ul');
    var itemListElement = document.createElement('li');
    itemListElement.textContent = "Teste";
    listElement.appendChild(itemListElement);
    
}*/