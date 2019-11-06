import repositoryService from 'src/services/RepositoryService';
import utils from 'src/app/model/ModelUtils';
import appConfig from 'src/services/AppConfigService';

const PATH = appConfig.apiUrl() + '/api/autoComplete/models'

const state = {
  filter: {
    itemsPerPage: 100,
    currentPage: 1,
    status: 'ENABLED'
  },
  list: [],
  pagination: {}
}

export const mutationsTypes = {
  LIST: 'model/LIST'
}

const mutations = {
  [mutationsTypes.LIST] (state, payload) {
    state.list = payload
  }
}

const actions = {

  selectModelSetList: ({commit}, payload) => {
    commit(mutationsTypes.LIST, payload)
  },

  selectModelSearch({dispatch, state}) {

    let url = utils.buildQuery(PATH, state.filter)

    return repositoryService.searchAutoComplete(url)
      .then(response => {
        dispatch('selectModelSetList', response.data)
      })
  }
}

const getters = {
  selectModelList: function (state) {
    return state.list
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
