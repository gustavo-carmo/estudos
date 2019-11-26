var listElement = document.querySelector('#app ul');
var inputElement = document.querySelector('#app input');
var buttonElement = document.querySelector('#app button');

var todoList = [
    'Fazer café',
    'Estudar Javascript',
    'Acessar a comunidade Rocketseat'
];


function renderTodos() {

    for (todo of todoList) {

        // Cria o elemento Link para a exclusão
        var todoLinkRemoveElement = document.createElement('a');
        var todoLinkRemoveText = document.createTextNode('Excluir');
        todoLinkRemoveElement.setAttribute('href', '#');
        todoLinkRemoveElement.appendChild(todoLinkRemoveText);

        // Cria o elemento Li que é o item da lista e adiciona o link de exclusão
        var todoElement = document.createElement('li');
        var todoText = document.createTextNode(todo);
        todoElement.appendChild(todoText);
        todoElement.appendChild(todoLinkRemoveElement);

        // Adiciona o elemento Li a Lista
        listElement.appendChild(todoElement);

    }

}

renderTodos();

/*
function addItemOnTodoList() {
    var listElement = document.querySelector('ul');
    var itemListElement = document.createElement('li');
    itemListElement.textContent = "Teste";
    listElement.appendChild(itemListElement);
    
}*/