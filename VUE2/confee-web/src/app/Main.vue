<template>
  <div id="app">
    <RootHeader></RootHeader>
    <div class="container">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import RootHeader from 'components/root/header/Main.vue'

export default {
  name: 'app',
  components: {
    RootHeader
  }
}
</script>

<style src="assets/sass/app.scss" lang="sass"></style>
