<template xmlns:v-on="http://www.w3.org/1999/xhtml">
    <div>
        <hr>
        <h1>Yaksoba</h1><br>

        <button v-on:click="backTo()">Voltar "Restaurante"</button>
    </div>
</template>

<script>

    export default {
        methods: {
            backTo: function () {
                this.$router.push({path: '/restaurante'});
            }
        }
    }

</script>