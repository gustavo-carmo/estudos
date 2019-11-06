import repositoryService from '../../services/RepositoryService';
import utils from './FormUtils';
import appConfig from '../../services/AppConfigService';
import localDatabase from '../../services/LocalDatabaseService';

const PATH = appConfig.apiUrl() + '/api/form'

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
  LIST: 'form/LIST',
  PAGINATION: 'form/PAGINATION',
  UPDATE_FILTER: 'form/UPDATE_FILTER',
  UPDATE_FILTER_CURRENT_PAGE: 'form/UPDATE_FILTER_CURRENT_PAGE',
  UPDATE_FILTER_ITEMS_PER_PAGE: 'form/UPDATE_FILTER_ITEMS_PER_PAGE'
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

  formSetList: ({commit}, payload) => {
    commit(mutationsTypes.LIST, payload)
  },

  formSetPagination: ({commit}, payload) => {
    commit(mutationsTypes.PAGINATION, payload)
  },

  formSetItemsPerPage({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, 1)
    commit(mutationsTypes.UPDATE_FILTER_ITEMS_PER_PAGE, payload)
    dispatch("formSearch")
  },

  formSetPage({dispatch, commit}, payload) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, payload)
    dispatch('formSearch')
  },

  formDoSearch({dispatch, commit}) {
    commit(mutationsTypes.UPDATE_FILTER_CURRENT_PAGE, 1)
    dispatch("formSearch")
  },

  formSearch({dispatch, state}) {
    dispatch("showLoader")
    let url = PATH;
    //let url = utils.buildQuery(PATH, state.filter);

    repositoryService.search(url)
      .then(response => {
        dispatch('formSetList', response.data);
        dispatch('formSetPagination', response.pagination);
        dispatch('hideLoader');
      })
  },

  formSave({}, payload) {
    let saveUrl = PATH;
    saveUrl += payload.id ? '/' + payload.id : '';

    return repositoryService.save(saveUrl, payload)
  },

  formGetInstance({}, payload) {
    return localDatabase.findById(payload);
  }
}

const getters = {
  formFilter: function (state) {
    return Object.assign({}, state.filter)
  },
  formList: function () {
    return state.list
  },
  formPagination: function () {
    return state.pagination
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
