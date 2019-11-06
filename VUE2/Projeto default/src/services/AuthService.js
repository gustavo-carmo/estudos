import http from 'src/http'
import appConfigService from './AppConfigService'

export default {

  login(tenantIdentifier, username, password) {

    const LOGIN_URL = appConfigService.apiUrl() + '/api/login';

    /*return http.post(LOGIN_URL, {
      credentials: {
        username: username,
        tenant_identifier: tenantIdentifier
      },
      password: password
    }).then(function (response) {
      return response.data
    }).catch(function (error) {
      console.log('login error...')
      throw error
    });*/

    /*return Promise.resolve(
      return {
        username: username,
        roles: []
      }
    );*/

    return new Promise(function(resolve) {
        resolve({
            username: username,
            roles: []
        })
      }
    );
  }
}
