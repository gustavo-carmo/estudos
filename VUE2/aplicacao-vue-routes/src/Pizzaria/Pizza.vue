<template>
    <div>
        <h1>Pizzaria Dominic</h1>
        <h2>Para saborear o simples gosto da italia</h2>
        <hr>
        <router-link to="/pizzaria/calabresa">Calabresa</router-link>
        <router-link to="/pizzaria/3queijos">3 Queijos</router-link>

        <router-view></router-view>
    </div>
</template>

<script>

    export default {}

</script>