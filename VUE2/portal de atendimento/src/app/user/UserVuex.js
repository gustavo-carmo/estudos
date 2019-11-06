import repositoryService from '../../services/RepositoryService';
import utils from './UserUtils';
import appConfig from '../../services/AppConfigService';
import localDatabase from '../../services/LocalDatabaseService';

const PATH = appConfig.apiUrl() + '/api/users'

const state = {
  filter: {
    itemsPerPage: 10,
    currentPage: 1,
    name: '',
    username: '',
    status: '',
    tenantId: ''
  },
  list: [],
  pagination: {}
}

export const mutationsTypes = {
  LIST: 'user/LIST',
  PAGINATION: 'user/PAGINATION',
  UPDATE_FILTER: 'user/UPDATE_FILTER',
  UPDATE_FILTER_CURRENT_PAGE: 'user/UPDATE_FILTER_CURRENT_PAGE',
  UPDATE_FILTER_ITEMS_PER_PAGE: 'user/UPDATE_FILTER_ITEMS_PER_PAGE'
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

  userSetList: ({commit}, payload) => {
    commit(mutationsTypes.LIST, payload)
  },

  userSetPagination: ({commit}, payload) => {
    commit(mutationsTypes.PAGINATION, payload)
  },

  userSetItemsPerPage({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, 1)
    commit(mutationsTypes.UPDATE_FILTER_ITEMS_PER_PAGE, payload)
    dispatch("userSearch")
  },

  userSetPage({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, payload)
    dispatch("userSearch")
  },

  userDoSearch({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER, payload)
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, 1)
    dispatch("userSearch")
  },

  userSearch({dispatch, state}) {

    dispatch("showLoader")

    let url = utils.buildQuery(PATH, state.filter)

    repositoryService.search(url)
      .then(response => {
        dispatch('userSetList', response.data)
        dispatch('userSetPagination', response.pagination)
        dispatch('hideLoader')
      })
  },

  userSave({}, payload) {
    let saveUrl = PATH
    saveUrl += payload.id ? '/' + payload.id : ''

    return repositoryService.save(saveUrl, payload)
  },

  userGetInstance({}, payload) {
    return localDatabase.findById('User_' + payload)
  }
}

const getters = {
  userFilter: function (state) {
    return Object.assign({}, state.filter)
  },
  userList: function () {
    return state.list
  },
  userPagination: function () {
    return state.pagination
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
