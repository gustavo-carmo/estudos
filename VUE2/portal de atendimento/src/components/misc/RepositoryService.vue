
<script>

    import AppConfig from '../../services/AppConfigService';
    import http from 'src/http';

    export default{
        resolveReference(reference, callback) {

            //TODO - TODOS PARAMETROS SÃO REQUERIDOS


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

            let url = AppConfig.apiUrl() + reference.location;

            http.get(url).then(function (response) {

                if (response.data.meta.code !== 200) {
                    alert('Ocorreu um erro inesperado!');
                    console.log(response.data.errors);
                } else {

                    callback(response.data.data);
                    // list.push(response.data.data);
                }

            }, function (response) {
                console.log(response);
            })
        },

        loadInstance(id, instanceType, callback) {

            // buscar no cache por algo assim:
            // cache get ENTITY_id (USER_10)

            let url = this.resolveUrl(instanceType, id);

            http.get(url).then(function (response) {

                callback(response.data.data);

            }, function (response) {
                console.log(response);
            })
        },

        resolveUrl(instanceType, id) {
            let url = AppConfig.apiUrl();
            if (instanceType === 'USER') {
                url = url + "/api/users/" + id;
            } else if ( instanceType === 'SERVICE_ORDER') {
                url = url + "/api/serviceOrders/" + id;
            } else if ( instanceType === 'TENANT') {
                url = url + "/api/tenants/" + id;
            } else if ( instanceType === 'CONTRACTOR') {
                url = url + "/api/contractors/" + id;
            } else if ( instanceType === 'MODEL') {
                url = url + "/api/models/" + id;
            } else if ( instanceType === 'SERVICE_OFFERED') {
                url = url + "/api/serviceOffereds/" + id;
            } else if ( instanceType === 'DEFECT') {
                url = url + "/api/defects/" + id;
            } else {
                alert("Falha ao resolver a url do tipo " + instanceType);
            }
            return url
        }
    }
</script>
