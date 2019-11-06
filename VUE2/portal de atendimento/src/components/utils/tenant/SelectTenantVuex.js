import repositoryService from 'src/services/RepositoryService';
import utils from 'src/app/tenant/TenantUtils';
import appConfig from 'src/services/AppConfigService';

const PATH = appConfig.apiUrl() + '/api/autoComplete/tenants'

const state = {
  filter: {
    itemsPerPage: 100,
    currentPage: 1,
    status: ''
  },
  list: [],
}

export const mutationsTypes = {
  LIST: 'tenant/LIST',
  UPDATE_FILTER: 'tenant/UPDATE_FILTER',
  UPDATE_FILTER_CURRENT_PAGE: 'tenant/UPDATE_FILTER_CURRENT_PAGE'
}

/* eslint-disable no-param-reassign */
const mutations = {
  [mutationsTypes.LIST] (state, payload) {
    state.list = payload
  },

  [mutationsTypes.UPDATE_FILTER] (state, payload) {
    state.filter = Object.assign({}, state.filter, payload)
  },

  [mutationsTypes.UPDATE_FILTER_CURRENT_PAGE] (state, payload) {
    state.filter.currentPage = payload
  }
}

const actions = {

  selectTenantSetList: ({commit}, payload) => {
    commit(mutationsTypes.LIST, payload)
  },

  selectTenantDoSearch({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER, payload)
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, 1)
    return dispatch("selectTenantSearch")
  },

  selectTenantSearch({dispatch, state}) {

    let url = utils.buildQuery(PATH, state.filter)

    return repositoryService.searchAutoComplete(url)
      .then(response => {
        dispatch('selectTenantSetList', response.data)
      })
  }
}

const getters = {
  selectTenantList: function (state) {
    return state.list
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
