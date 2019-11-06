<template>
  <div id="app">
    <!--<img src="./assets/logo.png">-->
    <hello></hello>

    <br>
    <el-button @click.prevent="setEn()">English</el-button>
    <el-button @click.prevent="setPtBr()">Português(Br)</el-button>
    <el-button @click.prevent="setEs()">{{ $t("message")}}</el-button>
    <h1>Testando</h1>
    {{ $t("message")}}
    <h1>Testando</h1>
    {{ $t("test")}}
    <h1>Testando</h1>
    {{ $t("el.datepicker.now")}}
  </div>
</template>

<script>
import hello from './components/Hello'

export default {
  name: 'app',
  components: {
    hello
  },
  methods: {
    setPtBr: function() {
      this.$i18n.locale = 'pt';
    },
    setEn: function() {
      this.$i18n.locale = 'en';
    },
    setEs: function() {
      this.$i18n.locale = 'es';
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
