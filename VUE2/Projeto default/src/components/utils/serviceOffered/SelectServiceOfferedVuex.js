import repositoryService from 'src/services/RepositoryService';
import utils from 'src/app/serviceOffered/ServiceOfferedUtils';
import appConfig from 'src/services/AppConfigService';

const PATH = appConfig.apiUrl() + '/api/autoComplete/serviceOffereds'

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
  LIST: 'serviceOffered/LIST'
}

const mutations = {
  [mutationsTypes.LIST] (state, payload) {
    state.list = payload
  }
}

const actions = {

  selectServiceOfferedSetList: ({commit}, payload) => {
    commit(mutationsTypes.LIST, payload)
  },

  selectServiceOfferedSearch({dispatch, state}, payload) {

    state.filter.name = payload;

    let url = utils.buildQuery(PATH, state.filter)

    return repositoryService.searchAutoComplete(url)
      .then(response => {
        dispatch('selectServiceOfferedSetList', response.data)
      })
  }
}

const getters = {
  selectServiceOfferedList: function (state) {
    return state.list
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
