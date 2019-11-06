var helloWorld = function () {
	var message = "Olá Mundo";
	return function() {
		return message;
	};
};

console.log(helloWorld()())
