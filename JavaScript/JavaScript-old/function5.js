var counter = (function() {

    var _value = 0;

    var _add = function () {
        return ++_value;
    }

    var _reset = function () {
        return _value = 0;
    }

    return {
        add: _add,
        reset: _reset
    };
})();

for (var i = 0; i < 10; i++) {

    counter.add();
}

console.log(counter.add());

counter.reset();

for (var i = 0; i < 5; i++) {
    counter.add();
}

console.log(counter.add());
