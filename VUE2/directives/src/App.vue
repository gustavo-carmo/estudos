<template>
  <div id="app">
    <img src="./assets/logo.png">
    <h1 v-text='msg'></h1>
    <div v-html='htmlInsert'></div>
    <h1 v-once>{{one}}</h1>
    <h1>{{one}}</h1><hr>
    v-bind de atributo<br>
    <a v-bind:href="link"> Vá para o google</a><br>

    v-on<br>
    <button type="button" v-on:click="hello">Clicar</button><br>

    v-on<br>
    <button type="button" v-on:keyup.enter="hello">Clicar</button><br>
    <hr>
    <button v-on:click="changeCanI()"> change</button>
    <h1 v-if="canI">if Gustavo</h1>
    <h1 v-show="canI">show Gustavo</h1>
    <hr>
    v-model
    <input type="text" v-model="name">

    <p>
      {{name}}
    </p>

    <br>
    <input type="number" v-model.number="number">
    <p>
    {{ typeof number }}
    </p>
    <hr>
    v-for<br>
    <ul>
        <li v-for="item in list">{{item.name}}</li>
      </ul>
  </div>
</template>

<script>

  import { orderBy } from 'lodash'

export default {
  data () {
    return {
      msg: 'Hello Vue!',
      htmlInsert: '<h2>HTML que esta sendo inserido na pagina</h2>',
      one: 'oneWayBinding',
      link: 'https://www.google.com.br/',
      canI: true,
      name: 'Gustavo',
      number: '',
      items: [
        {name:'maça' },
        {name:'uva'},
        {name:'abacaxi'},
        {name:'laranja'},
        {name:'morango'},
        {name:'abacate'}]

    }
  },
  methods: {
    hello () {
      alert("Oláááááaaa!!!");
    },
    changeCanI() {
      this.canI = !this.canI
    }
  },
  mounted () {
    setTimeout(() => this.one = 'twoWayBindNow', 3000);
  },
  computed: {
    list() {
        return orderBy(this.items, 'name');
    }
  }
}
</script>

<style>
body {
  font-family: Helvetica, sans-serif;
}
</style>
