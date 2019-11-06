import repositoryService from '../../services/RepositoryService';
import appConfig from '../../services/AppConfigService';
import localDatabase from '../../services/LocalDatabaseService';

const PATH = appConfig.apiUrl() + '/api/businessAreas';

const state = {
  list: []
}

export const mutationsTypes = {
  LIST: 'systems/LIST',
  PAGINATION: 'systems/PAGINATION',
  UPDATE_FILTER: 'systems/UPDATE_FILTER',
  UPDATE_FILTER_CURRENT_PAGE: 'systems/UPDATE_FILTER_CURRENT_PAGE',
  UPDATE_FILTER_ITEMS_PER_PAGE: 'systems/UPDATE_FILTER_ITEMS_PER_PAGE'
}

/* eslint-disable no-param-reassign */
const mutations = {
  [mutationsTypes.LIST] (state, payload) {
    state.list = payload
  },

  [mutationsTypes.PAGINATION] (state, payload) {
    state.pagination = payload
  },

  [mutationsTypes.UPDATE_FILTER] (state, payload) {
    state.filter = Object.assign({}, state.filter, payload)
  },

  [mutationsTypes.UPDATE_FILTER_CURRENT_PAGE] (state, payload) {
    state.filter.currentPage = payload
  },

  [mutationsTypes.UPDATE_FILTER_ITEMS_PER_PAGE] (state, payload) {
    state.filter.itemsPerPage = payload
  }
}

const actions = {

  systemsSetList: ({commit}, payload) => {
    commit(mutationsTypes.LIST, payload)
  },

  systemsSetPagination: ({commit}, payload) => {
    commit(mutationsTypes.PAGINATION, payload)
  },

  systemsSetItemsPerPage({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, 1)
    commit(mutationsTypes.UPDATE_FILTER_ITEMS_PER_PAGE, payload)
    dispatch("systemsSearch")
  },

  systemsSetPage({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, payload)
    dispatch('systemsSearch')
  },

  systemsDoSearch({dispatch }, payload) {
    dispatch("systemsSearch", payload)
  },

  systemsSearch({dispatch}, id) {
    dispatch("showLoader")

    let url = PATH + '/' + id + '/systems'

    repositoryService.search(url)
      .then(response => {
        dispatch('systemsSetList', response)
        dispatch('hideLoader')
      })
  },

  systemsSave({}, payload) {
    return repositoryService.save(PATH, payload, true)
  },

  systemsGetInstance({}, payload) {
    return localDatabase.findById('Systems_' + payload)
  }
}

const getters = {
  systemsFilter: function (state) {
    return Object.assign({}, state.filter)
  },
  systemsList: function () {
    return state.list
  },
  systemsPagination: function () {
    return state.pagination
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
