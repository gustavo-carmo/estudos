<template>
  <div id="app">
    <img src="./assets/logo.png">
    <h1>{{ msg }}</h1>
    <br>
    Direto na mensagem
    <br>
    <h1>{{ msg.split('').reverse().join('') }}</h1>
    <br>
    Utilizando o computed properties
    <br>
    <h1>{{msgReverse}}</h1>
  </div>
</template>

<script>
export default {
  data () {
    return {
      msg: 'Hello Vue!'
    }
  },
  computed: {
    msgReverse () {
      return this.msg.split('').reverse().join('')
    }
  }
}
</script>

<style>
body {
  font-family: Helvetica, sans-serif;
}
</style>
