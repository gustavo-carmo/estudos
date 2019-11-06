<script>

import RootHeader from 'components/root/header/main.vue'
import AppFooter from 'components/root/footer/Footer.vue'

import {mapActions} from 'vuex';

export default {
  components: { RootHeader, AppFooter },
  methods: {
      ...mapActions([
        'testConection'
      ])
  },
  mounted () {
    let self = this;

    /*self.testConection()
      .then( () => {
        console.log("Testando a conexão!");
      });*/
  }
}

</script>

<style src="assets/sass/app.scss" lang="sass"></style>
<style src="assets/css/app.css"></style>
<style src="assets/css/bootstrap-override.css"></style>
<style src="assets/css/element-override.css"></style>
<style src="assets/css/helpers.css"></style>

<template>
  <div id="app">
    <PageLoader></PageLoader>
    <RootHeader/>
    <router-view></router-view>
    <AppFooter/>
  </div>
</template>
