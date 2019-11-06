import Vue from 'vue'
import App from './App.vue'


Vue.filter('reverse', value => {
  return value.split('').reverse().join('');
})

new Vue({
  el: '#app',
  render: h => h(App)
})
