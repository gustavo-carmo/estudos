import store from '../mainVuex';

const isAuthRoute = route => {
  return route.path === '/auth'
}

const isLogged = () => {
  if (!store.getters.isLogged) {
    store.dispatch('refreshAuthUser')
  }
  return store.getters.isLogged
}

export default(to, from, next) => {
  if (!isLogged() && !isAuthRoute(to)) {
    next('/auth')
  } else if (isLogged() && isAuthRoute(to)) {
    next('/')
  } else {
    next();
    /*
    let role = to.meta.role
    
    if (!role) {
      next()
    } else {

      store.dispatch('userHasRole', role)
        .then(response => {
          if (response) {
            next()
          } else {
            next('/')
          }
        })
    }*/
  }
}
