import repositoryService from '../../services/RepositoryService';
import utils from './MenuUtils';
import appConfig from '../../services/AppConfigService';
import localDatabase from '../../services/LocalDatabaseService';

const PATH = appConfig.apiUrl() + '/api/menu'

const state = {
  filter: {
    itemsPerPage: 100,
    currentPage: 1,
    code: '',
    status: '',
    openingDate: '',
    closingDate: ''
  },
  list: [],
  pagination: {}
}

export const mutationsTypes = {
  LIST: 'menu/LIST',
  PAGINATION: 'menu/PAGINATION',
  UPDATE_FILTER: 'menu/UPDATE_FILTER',
  UPDATE_FILTER_CURRENT_PAGE: 'menu/UPDATE_FILTER_CURRENT_PAGE',
  UPDATE_FILTER_ITEMS_PER_PAGE: 'menu/UPDATE_FILTER_ITEMS_PER_PAGE'
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

  menuSetList: ({commit}, payload) => {
    commit(mutationsTypes.LIST, payload)
  },

  menuSetPagination: ({commit}, payload) => {
    commit(mutationsTypes.PAGINATION, payload)
  },

  menuSetItemsPerPage({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, 1)
    commit(mutationsTypes.UPDATE_FILTER_ITEMS_PER_PAGE, payload)
    dispatch("menuSearch")
  },

  menuSetPage({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, payload)
    dispatch('menuSearch')
  },

  menuDoSearch({dispatch, commit}) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, 1)
    dispatch("menuSearch")
  },

  menuSearch({dispatch, state}) {
    dispatch("showLoader")
    let url = PATH;
    //let url = utils.buildQuery(PATH, state.filter);

    repositoryService.search(url)
      .then(response => {
        dispatch('menuSetList', response.data);
        dispatch('menuSetPagination', response.pagination);
        dispatch('hideLoader');
      })
  },

  menuSave({}, payload) {
    let saveUrl = PATH;
    saveUrl += payload.id ? '/' + payload.id : '';

    return repositoryService.save(saveUrl, payload)
  },

  menuGetInstance({}, payload) {
    return localDatabase.findById(payload);
  }
}

const getters = {
  menuFilter: function (state) {
    return Object.assign({}, state.filter)
  },
  menuList: function () {
    return state.list
  },
  menuPagination: function () {
    return state.pagination
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
