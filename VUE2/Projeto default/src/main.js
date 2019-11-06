import Vue from 'vue'
import App from './App'
import router from './router'
import store from './mainVuex'
import Loader from './plugins/loader'
import VueMask from 'v-mask'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import locale from 'element-ui/lib/locale/lang/pt-br'

import ModulePanelTemplate from './components/root/main/ModulePanelTemplate.vue'

//TODO - MOVER PARA UM ARQUIVO SEPARADO
String.prototype.replaceAll = function(from, to){
  var str = this;
  var pos = str.indexOf(from);
  while (pos > -1){
    str = str.replace(from, to);
    pos = str.indexOf(from);
  }
  return (str);
}

Vue.use(Loader, store);
Vue.use(ElementUI, { locale });
Vue.use(VueMask);
Vue.component('modulePanel', ModulePanelTemplate)

/* eslint-disable no-new */
new Vue({
  router,
  store,
  el: '#app',
  render: h => h(App)
})
