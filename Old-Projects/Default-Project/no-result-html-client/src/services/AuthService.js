import http from 'src/http'
import appConfigService from './AppConfigService'

export default {

  login(username, password) {

    const LOGIN_URL = appConfigService.apiUrl() + '/api/login';

    //LOGIN REAL
    return http.post(LOGIN_URL, {
      credentials: {
        username: username
      },
      password: password
    }).then(function (response) {
      return response.data
    }).catch(function (error) {
      console.log('login error...')
      throw error
    });

    //LOGIN FAKE
    /*return new Promise(function(resolve) {
        resolve({
          username: username,
          roles: []
        })
      }
    );*/
  },

  loginFake(username, password) {
    return new Promise(function(resolve) {
        resolve({
          username: username,
          roles: []
        })
      }
    );
  }
}
