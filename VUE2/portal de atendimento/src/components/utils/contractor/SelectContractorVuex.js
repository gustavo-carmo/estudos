import repositoryService from 'src/services/RepositoryService';
import utils from 'src/app/contractor/ContractorUtils';
import appConfig from 'src/services/AppConfigService';

const PATH = appConfig.apiUrl() + '/api/autoComplete/contractors'

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
  LIST: 'contractor/LIST'
}

const mutations = {
  [mutationsTypes.LIST] (state, payload) {
    state.list = payload
  }
}

const actions = {

  selectContractorSetList: ({commit}, payload) => {
    commit(mutationsTypes.LIST, payload)
  },

  selectContractorSearch({dispatch, state}) {

    let url = utils.buildQuery(PATH, state.filter)

    return repositoryService.searchAutoComplete(url)
      .then(response => {
        dispatch('selectContractorSetList', response.data)
      })
  }
}

const getters = {
  selectContractorList: function (state) {
    return state.list
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
