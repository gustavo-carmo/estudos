import repositoryService from 'src/services/RepositoryService';
import utils from './CityUtils';
import appConfig from 'src/services/AppConfigService';

const PATH = appConfig.apiUrl() + '/api/autoComplete/cities'

const state = {
  filter: {
    itemsPerPage: 100,
    currentPage: 1
  },
  list: [],
  pagination: {}
}

export const mutationsTypes = {
  LIST: 'city/LIST'
}

const mutations = {
  [mutationsTypes.LIST] (state, payload) {
    state.list = payload
  }
}

const actions = {

  selectCitySetList: ({commit}, payload) => {
    commit(mutationsTypes.LIST, payload)
  },

  selectCitySearch({dispatch, state}) {

    let url = utils.buildQuery(PATH, state.filter)

    return repositoryService.searchAutoComplete(url)
      .then(response => {
        dispatch('selectCitySetList', response.data)
      })
  }
}

const getters = {
  selectCityList: function (state) {
    return state.list
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
