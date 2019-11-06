import repositoryService from '../../services/RepositoryService';
import utils from './BusinessAreaUtils';
import appConfig from '../../services/AppConfigService';
import localDatabase from '../../services/LocalDatabaseService';

const PATH = appConfig.apiUrl() + '/api/businessAreas'

const state = {
  list: []
}

export const mutationsTypes = {
  LIST: 'businessArea/LIST',
  PAGINATION: 'businessArea/PAGINATION',
  UPDATE_FILTER: 'businessArea/UPDATE_FILTER',
  UPDATE_FILTER_CURRENT_PAGE: 'businessArea/UPDATE_FILTER_CURRENT_PAGE',
  UPDATE_FILTER_ITEMS_PER_PAGE: 'businessArea/UPDATE_FILTER_ITEMS_PER_PAGE'
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

  businessAreaSetList: ({commit}, payload) => {
    commit(mutationsTypes.LIST, payload)
  },

  businessAreaSetPagination: ({commit}, payload) => {
    commit(mutationsTypes.PAGINATION, payload)
  },

  businessAreaSetItemsPerPage({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, 1)
    commit(mutationsTypes.UPDATE_FILTER_ITEMS_PER_PAGE, payload)
    dispatch("businessAreaSearch")
  },

  businessAreaSetPage({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, payload)
    dispatch('businessAreaSearch')
  },

  businessAreaDoSearch({dispatch, commit}, payload) {
    dispatch("businessAreaSearch")
  },

  businessAreaSearch({dispatch, state}) {
    dispatch("showLoader")

    let url = PATH

    repositoryService.search(url)
      .then(response => {
        dispatch('businessAreaSetList', response)
        dispatch('hideLoader')
      })
  },

  businessAreaSave({}, payload) {
    return repositoryService.save(PATH, payload, true)
  },

  businessAreaGetInstance({}, payload) {
    return localDatabase.findById('BusinessArea_' + payload)
  }
}

const getters = {
  businessAreaFilter: function (state) {
    return Object.assign({}, state.filter)
  },
  businessAreaList: function () {
    return state.list
  },
  businessAreaPagination: function () {
    return state.pagination
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
