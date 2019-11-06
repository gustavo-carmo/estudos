import Vue from 'vue'
import Vuex from 'vuex'
import Element from 'element-ui'

import App from './App'
import router from './router'

Vue.use(Vuex)
Vue.use(Element)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
