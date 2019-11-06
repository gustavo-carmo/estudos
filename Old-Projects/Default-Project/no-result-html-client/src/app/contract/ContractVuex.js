import repositoryService from '../../services/RepositoryService';
import utils from './ContractUtils';
import appConfig from '../../services/AppConfigService';
import localDatabase from '../../services/LocalDatabaseService';

const PATH = appConfig.apiUrl() + '/api/contract'

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
  LIST: 'contract/LIST',
  PAGINATION: 'contract/PAGINATION',
  UPDATE_FILTER: 'contract/UPDATE_FILTER',
  UPDATE_FILTER_CURRENT_PAGE: 'contract/UPDATE_FILTER_CURRENT_PAGE',
  UPDATE_FILTER_ITEMS_PER_PAGE: 'contract/UPDATE_FILTER_ITEMS_PER_PAGE'
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

  contractSetList: ({commit}, payload) => {
    commit(mutationsTypes.LIST, payload)
  },

  contractSetPagination: ({commit}, payload) => {
    commit(mutationsTypes.PAGINATION, payload)
  },

  contractSetItemsPerPage({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, 1)
    commit(mutationsTypes.UPDATE_FILTER_ITEMS_PER_PAGE, payload)
    dispatch("contractSearch")
  },

  contractSetPage({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, payload)
    dispatch('contractSearch')
  },

  contractDoSearch({dispatch, commit}) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, 1)
    dispatch("contractSearch")
  },

  contractSearch({dispatch, state}) {
    dispatch("showLoader")
    let url = PATH;
    //let url = utils.buildQuery(PATH, state.filter);

    repositoryService.search(url)
      .then(response => {
        dispatch('contractSetList', response.data);
        dispatch('contractSetPagination', response.pagination);
        dispatch('hideLoader');
      })
  },

  contractSave({}, payload) {
    let saveUrl = PATH;
    saveUrl += payload.id ? '/' + payload.id : '';

    return repositoryService.save(saveUrl, payload)
  },

  contractGetInstance({}, payload) {
    return localDatabase.findById(payload);
  }
}

const getters = {
  contractFilter: function (state) {
    return Object.assign({}, state.filter)
  },
  contractList: function () {
    return state.list
  },
  contractPagination: function () {
    return state.pagination
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
