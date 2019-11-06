import Vue from 'vue'
import App from './App'
import router from './router'

import store from './mainVuex'

import Loader from './plugins/loader'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import locale from 'element-ui/lib/locale/lang/pt-br'

import ModulePanelTemplate from './components/root/main/ModulePanelTemplate.vue'

Vue.use(Loader, store)
Vue.use(ElementUI, { locale })
Vue.component('modulePanel', ModulePanelTemplate)

/* eslint-disable no-new */
new Vue({
  router,
  store,
  el: '#app',
  render: h => h(App)
})
