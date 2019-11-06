import repositoryService from '../../services/RepositoryService';
import utils from './BusinessLineUtils';
import appConfig from '../../services/AppConfigService';
import localDatabase from '../../services/LocalDatabaseService';

const PATH = appConfig.apiUrl() + '/api/businessLine'

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
  LIST: 'businessLine/LIST',
  PAGINATION: 'businessLine/PAGINATION',
  UPDATE_FILTER: 'businessLine/UPDATE_FILTER',
  UPDATE_FILTER_CURRENT_PAGE: 'businessLine/UPDATE_FILTER_CURRENT_PAGE',
  UPDATE_FILTER_ITEMS_PER_PAGE: 'businessLine/UPDATE_FILTER_ITEMS_PER_PAGE'
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

  businessLineSetList: ({commit}, payload) => {
    commit(mutationsTypes.LIST, payload)
  },

  businessLineSetPagination: ({commit}, payload) => {
    commit(mutationsTypes.PAGINATION, payload)
  },

  businessLineSetItemsPerPage({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, 1)
    commit(mutationsTypes.UPDATE_FILTER_ITEMS_PER_PAGE, payload)
    dispatch("businessLineSearch")
  },

  businessLineSetPage({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, payload)
    dispatch('businessLineSearch')
  },

  businessLineDoSearch({dispatch, commit}) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, 1)
    dispatch("businessLineSearch")
  },

  businessLineSearch({dispatch, state}) {
    dispatch("showLoader")
    let url = PATH;
    //let url = utils.buildQuery(PATH, state.filter);

    repositoryService.search(url)
      .then(response => {
        dispatch('businessLineSetList', response.data);
        dispatch('businessLineSetPagination', response.pagination);
        dispatch('hideLoader');
      })
  },

  businessLineSave({}, payload) {
    let saveUrl = PATH;
    saveUrl += payload.id ? '/' + payload.id : '';

    return repositoryService.save(saveUrl, payload)
  },

  businessLineGetInstance({}, payload) {
    return localDatabase.findById(payload);
  }
}

const getters = {
  businessLineFilter: function (state) {
    return Object.assign({}, state.filter)
  },
  businessLineList: function () {
    return state.list
  },
  businessLinePagination: function () {
    return state.pagination
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
