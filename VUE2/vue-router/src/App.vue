<template>
    <div id="app">
        <img src="./assets/logo.png">
        <h1>{{ msg }}</h1>
    </div>
    <hr>
    ei mãe eu tenho uma guitarra eletrica
    <hr>
    <router-view></router-view>
</template>

<script>
    export default {
        data () {
            return {
                msg: 'Hello Vue!'
            }
        }
    }
</script>

<style>
    body {
        font-family: Helvetica, sans-serif;
    }
</style>
