import authService from '../../services/AuthService'
import utilsService from '../../services/UtilsService'
import http from '../../http'

export const mutationsTypes = {
  LOGIN: 'auth/LOGIN',
  LOGOUT: 'auth/LOGOUT',
  MENU_MAIN_ACTIVE: 'auth/MENU_MAIN_ACTIVE',
  MENU_MAIN: 'auth/MENU_MAIN'
}

const state = {
  user: {},
  logged: false,
  menus: {
    main: [
      {
        menuItem: 'exampleItem',
        active: false,
        role: 'ROLE_EXAMPLE'
      }
    ]
  }
}

/* eslint-disable no-param-reassign */
const mutations = {
  [mutationsTypes.LOGIN] (state, payload) {
    state.user = payload
    state.logged = true
  },

  [mutationsTypes.LOGOUT] (state) {
    state.user = ''
    state.logged = false
  },

  [mutationsTypes.MENU_MAIN_ACTIVE] (state, payload) {
    state.menus.main[payload.index].active = payload.active
  },

  [mutationsTypes.MENU_MAIN] (state, payload) {
    state.menus.main = payload
  }
}

const actions = {

  login: ({dispatch}, payload) => {

    return authService.login(
      payload.username,
      payload.password)
      .then(user => {

        utilsService.localStorageSetItem(mutationsTypes.LOGIN, user)
        dispatch('buildUser', user)

        return user;
      }, error => {
        throw error
      })
  },

  logout: ({commit}) => {
    utilsService.localStorageSetItem(mutationsTypes.LOGIN, '')
    commit(mutationsTypes.LOGOUT)
    http.defaults.headers.common['Authorization'] = ''
  },

  refreshAuthUser: ({dispatch}) => {

    let userFromLocalStorage = utilsService.localStorageGetItem(mutationsTypes.LOGIN)

    if (!state.logged && userFromLocalStorage && userFromLocalStorage !== '') {
      dispatch('buildUser', userFromLocalStorage)
    }
  },

  buildMenuMain({commit, dispatch, state}) {

    for (let i = 0; i < state.menus.main.length; i++) {
      dispatch('userHasRole', state.menus.main[i].role)
        .then(response => {
          commit(mutationsTypes.MENU_MAIN_ACTIVE, {index: i, active: response})
        })
    }
  },

  userHasRole({}, payload) {
    let roles = state.user.roles

    if (roles) {
      if (roles.indexOf(payload) >= 0) {
        return true
      }
    }
    return false
  },

  buildUser({commit, dispatch}, user) {

    commit(mutationsTypes.LOGIN, user)
    http.defaults.headers.common['Authorization'] = 'Bearer ' + user.access_token
    dispatch('buildMenuMain')
  }
}

const getters = {

  isLogged: () => {
    return state.logged
  },

  loggedUser: function(state) {
    return state.logged ? state.user : null
  },

  authMenuMainExample: function () {
    return findItemMenuMain(state.menus.main, 'exampleItem').active
  }
}

const findItemMenuMain = function(list, itemFind) {
  return list.filter((item) => {
    return item.menuItem == itemFind;
  })[0]
}

export default {
  state,
  mutations,
  actions,
  getters
}
