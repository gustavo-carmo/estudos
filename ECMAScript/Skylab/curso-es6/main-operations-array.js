const arr = [1, 3, 4, 5, 8, 9];

const map = arr.map(function (item, index) {
  console.log("Percorrendo o indice: ", index);
  return item * 2;
});

console.log("Map: ", map);

const sum = arr.reduce(function(total, next) {
  return total + next;
});

console.log("Reduce: ", sum);

const filter = arr.filter(function (item) {
  return item % 2 === 0;
});

console.log("Filter: ", filter);

const find = arr.find(function (item) {
  return item > 5;
});

console.log("Find: ", find);