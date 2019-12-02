// Aula 1 - introdução ao AJAX

var xhr = new XMLHttpRequest();

xhr.open('GET', 'https://api.github.com/users/gustavo-carmo');
xhr.send(null);

xhr.onreadystatechange = function() {

    if (xhr.readyState === 4) {

        var responseJson = xhr.responseText;
        console.log('Request 1 - Sucesso : ', JSON.parse(responseJson));
    }
}

// Aula 2 - Promisses

var myPromise = function () {
    
    return new Promise(function(resolve, reject) {

        var xhr2 = new XMLHttpRequest();

        xhr2.open('GET', 'https://api.github.com/users/gustavo-carmo');
        xhr2.send(null);

        xhr2.onreadystatechange = function() {

            if(xhr2.readyState === 4) {

                if(xhr2.status === 200) {

                    resolve(JSON.parse(xhr2.responseText));
                } else {

                    reject('Erro na requisição');
                }

            }
        }
    });
}

myPromise().then( function(response) {

    console.log('Request 2 - Sucesso : ', response);
}).catch(function(error) {

    console.warn('Request 2 - Erro : ', error);
});

// Aula 3 - Axios

axios.get('https://api.github.com/users/gustavo-carmo').then( function(response) {

    console.log('Request 3 - Sucesso : ', response);
    console.log('Request 3 - Sucesso - Avatar Url : ', response.data.avatar_url);
}).catch(function(error) {

    console.warn('Request 3 - Erro : ', error);
});

// ***************************** DESAFIO *****************************

// Exercicio 1
var ageVerifier = function (age) {

    return new Promise((resolve, reject) => {

        if (age >= 18) {

            resolve("Maior de idade");
        } else {

            reject("Menor de idade");
        }
    });
}

ageVerifier(13).then( function(resolve) {

    console.log("Maior que 18 anos");
}).catch(function(error) {

    console.warn("Menor que 18 anos");
});

// Exercicio 2 e 3

var listElement = document.querySelector('#app ul');
var inputElement = document.querySelector('#app input');
var buttonElement = document.querySelector('#app button');

var reposList = [];

function clearList() {

    listElement.innerHTML = '';
}

function renderReposList() {

    clearList();

    for (repo of reposList) {

        var repoElement = document.createElement('li');
        var repoElementText = document.createTextNode(repo);
        repoElement.appendChild(repoElementText);
        repoElement.setAttribute('class', 'list-group-item');
        repoElement.setAttribute('style', 'margin-top: 5px; border: 1px solid;');

        listElement.appendChild(repoElement);
    }
}

function addMessage(message) {

    var repoElement = document.createElement('li');
    var repoElementText = document.createTextNode(message);
    repoElement.appendChild(repoElementText);
    repoElement.setAttribute('class', 'list-group-item');
    repoElement.setAttribute('id', 'message');
    repoElement.setAttribute('style', 'margin-top: 5px; border: 1px solid;');

    listElement.appendChild(repoElement);
}

function removeMessage() {

    var messageToRemoveElement = document.querySelector('#app ul li#message');

    if (messageToRemoveElement !== null) {
        listElement.removeChild(messageToRemoveElement);
    }
}

function searchRepo() {
    
    reposList = [];
    clearList();
    removeMessage();
    addMessage('Carregando ...');
    var gitUsername = inputElement.value;

    axios.get(`https://api.github.com/users/${gitUsername}/repos`).then(response => {
        
        if (response.status === 200) {
            
            for(repo of response.data) {
                reposList.push(repo.name);
            }
        } 

    }).then(() => {

        removeMessage();
        renderReposList();
    }).catch(error => {
        removeMessage();
        addMessage(`Ocorreu um erro ao localizar o usuário ${gitUsername} - Erro: ${error}`);
        console.warn('Request 3 - Erro : ', error);
    });
}

buttonElement.onclick = searchRepo;