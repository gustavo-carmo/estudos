<script>

    import Auth from '../misc/Auth.vue';
    import Utils from '../utils/Utils.vue';
    import AppConfig from '../misc/AppConfig.vue';
    import RepositoryService from '../misc/RepositoryService.vue';
    const PATH_USERS = AppConfig.apiUrl() + '/api/users';

    export default{
        searchUser(http, filter, callbackEachRow, callbackPagination) {

            let query = [];

            if (filter.name) {
                query.push("name=" + filter.name);
            }
            if (filter.username) {
                query.push("username=" + filter.username);
            }
            if (filter.enabled) {
                query.push("enabled=" + filter.enabled);
            }
            if (filter.currentPage) {
                query.push("currentPage=" + filter.currentPage);
            }
            if (filter.itemsPerPage) {
                query.push("itemsPerPage=" + filter.itemsPerPage);
            }
            /*if (filter.lastLoginStart) {
             query.push("lastLoginStart=" + Utils.splitDate(filter.lastLoginStart));
             }
             if (filter.lastLoginEnd) {
             query.push("lastLoginEnd=" + Utils.splitDate(filter.lastLoginEnd));
             }*/

            let usersUrl = PATH_USERS;
            if (query.length) {
                usersUrl = usersUrl + "?" + query.join("&");
            }

            let headers = {headers: Auth.getAuthorizationHeader()};
            http.get(usersUrl, headers).then(function (response) {

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
        getUser(http, id, callback) {
            RepositoryService.loadInstance(
                    http,
                    id,
                    'USER',
                    callback
            );
        },
        saveUser(http, paramUser, callbackSuccess, callbackError) {

            let self = this;

            var user = paramUser;
            user.enabled = paramUser.enabled ? true : false;

            let httpRequest;

            let headers = {headers: Auth.getAuthorizationHeader()};

            if (user.id) {
                httpRequest = http.put(PATH_USERS + "/" + user.id, JSON.stringify(user), headers);
            } else {
                httpRequest = http.post(PATH_USERS, JSON.stringify(user), headers);
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
        },
        findUserRoles(http, id, callbackEachRow, callbackPagination, callBackOnFinish) {
            let userRolesUrl = PATH_USERS + '/' + id + '/roles';
            let headers = {headers: Auth.getAuthorizationHeader()};

            http.get(userRolesUrl, headers).then(function (response) {
                callbackPagination(response.body.pagination);

                let x;
                for (x in response.body.data) {
                    RepositoryService.resolveReference(
                            http,
                            response.body.data[x],
                            callbackEachRow
                    );
                }

                callBackOnFinish();

            }, function (response) {

                console.log(response);

            }).finally(function () {
                // console.log('fim.......');
            });
        },
        saveUserRoles(http, id, paramRoles, callbackSuccess, callbackError) {

            let self = this;
            let url = PATH_USERS + "/" + id + "/roles";
            var roles = {
                "roles": paramRoles
            };

            let headers = {headers: Auth.getAuthorizationHeader()};

            http.put(url, JSON.stringify(roles), headers).then(function (response) {
                let body = response.body;

                if (body.meta.code == 200) {
                    console.log("callbackSuccess");
                    callbackSuccess();
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
        },
    }
</script>
