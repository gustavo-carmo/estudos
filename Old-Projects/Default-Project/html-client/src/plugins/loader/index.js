import loaderFactory from './loaderFactory'
import registerStore from './registerStore'
import PageLoader from './components/page-loader.vue'

const install = (Vue, store) => {
  Vue.component('PageLoader', PageLoader)

  registerStore(store)
  Object.defineProperty(Vue.prototype, '$loader', {
    get () {
      return loaderFactory(this)
    }
  })
}

export default {install}
