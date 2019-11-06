import repositoryService from 'src/services/RepositoryService';
import appConfig from 'src/services/AppConfigService';

//TODO - USAR ALGUMA URI PARA TESTAR A CONEXÃO
//const PATH = appConfig.apiUrl() + '/api/autoComplete/models';

const actions = {

  testConection() {
    return repositoryService.searchAutoComplete(PATH);
  }
}

export default {
  actions
}
