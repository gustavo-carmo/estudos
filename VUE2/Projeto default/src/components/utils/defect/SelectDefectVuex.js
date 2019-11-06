import repositoryService from 'src/services/RepositoryService';
import utils from 'src/app/defect/DefectUtils';
import appConfig from 'src/services/AppConfigService';

const PATH = appConfig.apiUrl() + '/api/autoComplete/defects'

const state = {
  filter: {
    itemsPerPage: 100,
    currentPage: 1,
    name: '',
    status: 'ENABLED'
  },
  list: [],
  pagination: {}
}

export const mutationsTypes = {
  LIST: 'defect/LIST'
}

const mutations = {
  [mutationsTypes.LIST] (state, payload) {
    state.list = payload
  }
}

const actions = {

  selectDefectSetList: ({commit}, payload) => {
    commit(mutationsTypes.LIST, payload)
  },

  selectDefectSearch({dispatch, state}, payload) {

    state.filter.name = payload;

    let url = utils.buildQuery(PATH, state.filter)

    return repositoryService.searchAutoComplete(url)
      .then(response => {
        dispatch('selectDefectSetList', response.data)
      })
  }
}

const getters = {
  selectDefectList: function (state) {
    return state.list
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
