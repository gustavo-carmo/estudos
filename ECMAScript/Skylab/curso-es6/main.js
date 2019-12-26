// REST

const usuario = {
  nome: "Gustavo",
  idade: 24,
  empresa: "Rocketseat"
};

const { nome, ...restoUsuario } = usuario;

console.log("Nome -> ", nome);
console.log("Resto Usuário -> ", restoUsuario);

const arr = [1, 2, 3, 4];

const [a, b, ...c] = arr;

console.log(a);
console.log(b);
console.log(c);

function soma (a, b, ...params) {
  console.log(a, b);
  return params.reduce((total, next) => total + next);
}

console.log(soma("Bla", "teste", 2,3,4,5,6));