import AppConfig from './AppConfigService';
import http from '../http';
import localDatabase from './LocalDatabaseService'
import utilsService from './UtilsService'

export default {

  searchAutoComplete(url) {
    let self = this;

    return http.get(url)
      .then(response => {
        return {
          code: response.data.meta.code,
          data: response.data.data,
          pagination: response.data.pagination
        }
      })
  },

  search(url) {
    let self = this;
    let pagination;

    return http.get(url)
      .then(response => {

        pagination = response.data.pagination;

        let promises = response.data.data.map(object => {
          return self.loadObject(object);
        })

        return Promise.all(promises)
          .then(response => {

            return {
              data: response,
              pagination: pagination
            }
          })
      })
  },

  loadObject(object) {

    let id  = object.id;

    return localDatabase.findById(id)
      .then(document => {
        if (!document) {
          return localDatabase.save(id, object);
        }

        return localDatabase.update(document, object);
      })
  },

  load(entityLocation) {

    let url = AppConfig.apiUrl() + entityLocation.location

    return http.get(url)
      .then(response => {
        return response.data.data
      })
  },

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
        // console.log(response.data.errors);
      } else {

        callback(response.data.data);
        // list.push(response.data.data);
      }

    }, function (response) {
      // console.log(response);
    })
  },

  save(url, form, notLoadEntityLocation) {
    let self = this;

    let dataSend = JSON.stringify(form);

    return http({
      method: form.id ? 'put' : 'post',
      url: url,
      data: JSON.stringify(form)
    })
      .then(response => {
        return response.data
      })
      .then(response => {
        let code = response.meta.code
        if (code == 200 || code == 201) {

          if(notLoadEntityLocation) {
            return response.data
          }

          return self.loadObject(response.data)
        } else if (code === 400) {
          console.log("Resposta de Erro no servidor!!!");
          console.log(response.errors);
          throw utilsService.buildErrorMessage(response.errors)
        }
      });
  }
}
