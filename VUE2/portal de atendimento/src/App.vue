<template>
  <div id="app">
    <PageLoader></PageLoader>
    <RootHeader/>
    <router-view></router-view>
    <AppFooter/>
  </div>
</template>

<script>

import RootHeader from 'components/root/header/main.vue'
import AppFooter from 'components/root/footer/Footer.vue'

export default {
  components: { RootHeader, AppFooter }
}

</script>

<style src="assets/sass/app.scss" lang="sass"></style>
<style src="assets/css/app.css"></style>
<style src="assets/css/bootstrap-override.css"></style>
<style src="assets/css/helpers.css"></style>
