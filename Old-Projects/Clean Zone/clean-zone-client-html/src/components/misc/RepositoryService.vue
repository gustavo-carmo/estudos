<script>

    import Auth from './Auth.vue';
    import AppConfig from './AppConfig.vue';

    export default{
        resolveReference(http, reference, callback) {

            // TODO - cache local
            // formato do cache: users: [{
            //   TODO - existem libs para isso...
            // }]
            // o registro existe no cache?
                // sim: o version é o mesmo?
                    // sim: continue to devolve....
                    // não
                        // remove do cache
                        // busca no servidor
                        // insere no cache
                        // continue to devolve....
                // não
                    // busca no servidor
                    // insere no cache
                    // continue to devolve....
            // devolve do cache, FIM

            let headers = {headers: Auth.getAuthorizationHeader()};
            let url = AppConfig.apiUrl() + reference.location;

            http.get(url, headers).then(function (response) {

                callback(response.body.data);
                // list.push(response.body.data);

            }, function (response) {
                console.log(response);
            }).finally(function () {
                // console.log('fim.......');
            });
        },

        loadInstance(http, id, instanceType, callback) {

            // buscar no cache por algo assim:
            // cache get ENTITY_id (USER_10)

            let headers = {headers: Auth.getAuthorizationHeader()};
            let url = this.resolveUrl(instanceType, id);

            http.get(url, headers).then(function (response) {

                callback(response.body.data);

            }, function (response) {
                console.log(response);
            }).finally(function () {
                // console.log('fim.......');
            });
        },

        resolveUrl(instanceType, id) {
            let url = AppConfig.apiUrl();
            if (instanceType === 'USER') {
                url = url + "/api/users/" + id;
            }
            return url
        }
    }
</script>
