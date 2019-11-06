// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import ElementUI from 'element-ui';
import VueI18n from 'vue-i18n';

import messages from './i18n';

Vue.use(VueI18n);

const i18n = new VueI18n({
  locale: 'en',
  messages
});

Vue.use(ElementUI, {
  i18n: Key => i18n.t(key)
});

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  i18n,
  template: '<App/>',
  components: { App }
});
