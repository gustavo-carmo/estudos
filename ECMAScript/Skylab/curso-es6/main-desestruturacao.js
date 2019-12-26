const usuario = {
  nome: "Gustavo",
  idade: 24,
  endereco: {
    cidade: "Itu",
    estado: "SP"
  }
};

console.log(usuario.nome);
console.log(usuario.idade);
console.log(usuario.endereco.cidade);

const { nome, idade, endereco: { cidade } } = usuario;

console.log(nome);
console.log(idade);
console.log(cidade);

function mostraNome ({ nome, idade }) {
  console.log(nome, idade);
}

mostraNome(usuario);