import {defaults} from 'lodash'
import axios from 'axios'
import store from './mainVuex';

export const createClient = (options = {}) => {

  let http = axios.create(defaults({}, options))
  http.defaults.headers.post['Content-Type'] = 'application/json';
  http.defaults.headers.put['Content-Type'] = 'application/json';

  http.interceptors.response.use(response => {
      return response
    }, error => {

      var currentUrl = window.location.href;

      if (!error.response) {
        if (currentUrl.indexOf('/auth') < 0) {
          alert("Erro inesperado!\nVocê será redirecionado para a tela de login");
          store.dispatch('logout')
            .then(() => {
              window.location.reload();
            });
        }
      } else if (error.response.status == 401) {

        if (currentUrl.indexOf('/auth') < 0) {
          store.dispatch('logout')
            .then(() => {
              window.location.reload();
            });
        }
      }
      return Promise.reject(error);
    }
  );
  return http
}

export default createClient()
