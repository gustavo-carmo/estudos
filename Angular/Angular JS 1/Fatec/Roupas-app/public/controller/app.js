/*
 * Criamos o nosso modulo
 * Observe que estamos carregando os seguintes modulos externos:
 * ngRoute: controle de rotas
 * ngMask: criação de máscaras de entrada de dados 
 **/
var app = angular.module('estoqueApp', ['ngRoute', 'ngMask'])
    //Define o roteamento de acordo com a URL informada pelo usuario
    //Dependendo da URL irá verificar se o usuario está logado
    .config(['$routeProvider', function ($routeProvider) {
        ''
        //Definindo qual view será aberta em cada rota da aplicação
        $routeProvider
            .when('/menu', {
                templateUrl: 'views/menu.html'
            })
            .when('/customers', {
                templateUrl: 'views/customers.html'
            })
            .otherwise({
                redirectTo: '/menu'
            });
    }])

app.controller('estoqueController',
    function ($scope, $http, $window, $rootScope) {
        /*
         * 
         * RESTFUL SERVICES
         * 
         **********************************************************/
        var urlBase = 'http://localhost:3000';
        /*********************************************************
         *              
         */
        $scope.mensagem = { cor: 'success', titulo: '' }

        /*========================================================
         * PRODUTOS
         ========================================================*/
        
        // Carrega todos os customers 
        $scope.carregaCustomers = function () {
            getCustomers();
        };
        function getCustomers() {
            $scope.dados = { customers: null, customer: null }
            /*$http({
                method: 'get',
                url: urlBase + '/customers'
            }).then(function (response) {
                $scope.dados = { customers: response.data }
            }, function (error) {
                $scope.mensagem = { cor: 'danger', titulo: 'Não foi possível obter os customers. Verifique o backend!' + error.data.message }
            });
            */
           $scope.dados = [];
        }
        // Carrega os dados do customer pelo Id para a edição
        $scope.obtemCustomerPeloId = function (idCustomer) {
            $http({
                method: 'get',
                url: urlBase + '/customers/' + idCustomer
            }).then(function (response) {
                $scope.dados = { customer: response.data, customers: $scope.dados.customers }
            }, function (error) {
                $scope.mensagem = { cor: 'danger', titulo: error.data.message }
            });
        }
        // Exibe caixa de diálogo para a exclusão
        $scope.confirmaExclusaoCustomer = function (customer) {
            if (confirm('Confirma a exclusão do customer ' + customer.nome + ' ?')) {
                $http({
                    method: 'delete',
                    url: urlBase + '/customers/' + customer._id
                }).then(function (response) {
                    $scope.mensagem = { cor: 'success', titulo: response.data.message }
                    getCustomers()
                }, function (error) {
                    $scope.mensagem = { cor: 'danger', titulo: error.data.message }
                });
                
            }
        }
        // Função para salvar (insert ou update) os dados do customer
        $scope.salvaCustomer = function (customer) {
            if (customer._id === undefined) { //se ainda não existir o _id, iremos incluir (POST), senão alteramos pelo (PUT)
                $http({
                    method: 'post',
                    url: urlBase + '/customers/',
                    data: customer
                }).then(function (response) {
                    $scope.mensagem = { cor: 'success', titulo: 'Customer incluído com sucesso!' }
                    getCustomers()
                }, function (error) {
                    $scope.mensagem = { cor: 'danger', titulo: error.data.message }
                })
            } else {
                $http({
                    method: 'put',
                    url: urlBase + '/customers/' + customer._id,
                    data: customer
                }).then(function (response) {
                    $scope.mensagem = { cor: 'success', titulo: 'Customer alterado com sucesso!' }
                    getCustomers()
                }, function (error) {
                    $scope.mensagem = { cor: 'danger', titulo: error.data.message }
                })
            }
            
        }
        /*========================================================
         * FIM das Funções relacionados aos PRODUTOS
         ========================================================*/



    });

app.controller('horaController', function ($scope, $interval) {
    var tick = function () {
        $scope.clock = Date.now();
    };
    tick();
    $interval(tick, 1000);
});



