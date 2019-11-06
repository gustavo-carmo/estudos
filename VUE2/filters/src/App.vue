<template>
  <div id="app">
    <img src="./assets/logo.png">

    <input v-model="msg">
    <h1>{{ msg | reverse}}</h1>
  </div>
</template>

<script>
export default {
  data () {
    return {
      msg: ''
    }
  }
}
</script>

<style>
body {
  font-family: Helvetica, sans-serif;
}
</style>
