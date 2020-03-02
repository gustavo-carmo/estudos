class TodoList {

  constructor() {
    this.todos = [];
  }

  addTodo() {
    const bla = 'Teste'
    this.todos.push(`Novo todo ${bla}`);
    console.log(this.todos);
  }
}

const minhaLista = new TodoList();

