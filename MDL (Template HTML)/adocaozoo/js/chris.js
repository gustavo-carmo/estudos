


function home() {
    $("#retorno-ajax").load("home.html", function () {
        componentHandler.upgradeDom();
    });
}

function adoption() {
    $("#retorno-ajax").load("adoption.html", function() {
        componentHandler.upgradeDom();
    });
}

function contact() {
    $("#retorno-ajax").load("contact.html", function() {
        componentHandler.upgradeDom();
    });
}

function about() {
    $("#retorno-ajax").load("about.html", function() {
        componentHandler.upgradeDom();
    });
}

function contact_confirm(){
    $("#retorno-ajax").load("contact_confirm.html", function (){
        componentHandler.upgradeDom();
    });
}

$('body').ajaxComplete(function () {
     componentHandler.upgradeDom();
});

$( document ).ready(function() {
    home();
});
