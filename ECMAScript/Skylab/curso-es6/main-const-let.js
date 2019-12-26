const x = 4;

// x = 2; -- quando se trabalha com constantes não é possivel reatribuir um
// valor a constante, apenas mutar esse valor

const usuario = { nome: 'Gustavo' }

// usuario.nome = 7 -- em uma mutação é possivel alterar o tipo da variavel

console.log(usuario);

function teste(x) {

  let y = 4; // uma variavel de scopo só existe dentro de seu contexto
  // se eu tentar utilizar o y fora da função vai dar um erro informando que
  // o y não existe

  if (x > y) {
    console.log(x, y);
  }
}

teste(30);