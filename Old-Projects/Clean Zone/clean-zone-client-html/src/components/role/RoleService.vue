<script>
    import Auth from '../misc/Auth.vue';
    import Utils from '../utils/Utils.vue';
    import AppConfig from '../misc/AppConfig.vue';
    import RepositoryService from '../misc/RepositoryService.vue';

    const PATH_ROLES = AppConfig.apiUrl() + '/api/roles';

    export default{
        searchRole(http, filter, callbackEachRow, callbackPagination) {

            let query = [];

            if (filter.currentPage) {
                query.push("currentPage=" + filter.currentPage);
            }
            if (filter.itemsPerPage) {
                query.push("itemsPerPage=" + filter.itemsPerPage);
            }

            let roleUrl = PATH_ROLES;
            if (query.length) {
                roleUrl = roleUrl + "?" + query.join("&");
            }

            let headers = {headers: Auth.getAuthorizationHeader()};
            http.get(roleUrl, headers).then(function (response) {

                callbackPagination(response.body.pagination);

                let x;
                for (x in response.body.data) {
                    RepositoryService.resolveReference(
                            http,
                            response.body.data[x],
                            callbackEachRow
                    );
                }

            }, function (response) {

                console.log(response);

            }).finally(function () {
                // console.log('fim.......');
            });

        },
        getRole(http, id, callback) {
            RepositoryService.loadInstance(
                    http,
                    id,
                    'ROLE',
                    callback
            );
        },
        /*saveUser(http, paramUser, callbackSuccess, callbackError) {

            let self = this;

            var user = paramUser;
            user.enabled = paramUser.enabled ? true : false;

            let httpRequest;

            let headers = {headers: Auth.getAuthorizationHeader()};

            if (user.id) {
                httpRequest = http.put(PATH_ROLES + "/" + user.id, JSON.stringify(user), headers);
            } else {
                httpRequest = http.post(PATH_ROLES, JSON.stringify(user), headers);
            }

            httpRequest.then(function (response) {

                let body = response.body;

                if (body.meta.code == 200 || body.meta.code == 201) {
                    self.getUser(http, body.data.id, callbackSuccess);
                } else if (body.meta.code == 400) {
                    callbackError(Utils.buildErrorMessage(body.errors));
                }

            }, function (error) {

                // TODO - criar método genérico para tratar erro
                console.log("-----------------------------");
                console.log("Erro inesperado ao tentar salvar o usuário");
                console.log(error);
                console.log("-----------------------------");

            }).finally(function () {
                // console.log('fim.......');
            });
        }*/
    }
</script>
