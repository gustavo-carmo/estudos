<template>
    <div>
        {{resolveType()}}
    </div>
</template>

<script>
    export default{
        props: ["value"],
        methods: {
            resolveType: function () {

              let labels = {
                'NO_LINK': 'Sem Link',
                'FORM': 'Formulario'
              }

              return labels[this.value]
            }
        }
    }
</script>
