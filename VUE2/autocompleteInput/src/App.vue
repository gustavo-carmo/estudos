<template>
  <div id="app">
    <input type="text" v-mask="'####-####'" v-model="myInputModel" /><br>
    <MaskedInput v-model="date" mask="11/11/1111" placeholder="dd/mm/yyyy" /><br>
    <h1>ABOBRINHA</h1><br>
    {{msg}}
    <p>{{date}}</p>
    <p>{{myInputModel}}</p>
   
  </div>
</template>

<script>

  import MaskedInput from 'vue-masked-input'

  export default {
    name: 'app',
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        date: '',
        myInputModel: ''
      }
    },
    components: { 
      MaskedInput
    },
    methods: {
      processJSON(json) {
        return json.results;
      },
      getData(data) {
        console.log(data);
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

h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
