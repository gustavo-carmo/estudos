<template>
    <span class="label"  :class="{ 'label-success' : status == 'ENABLED', 'label-danger' : status == 'DISABLED', 'label-info' : status == 'NOT_FOUND' }">
        {{resolveStatus()}}
    </span>
</template>

<script>
    export default{
        props: ["status"],
        methods: {
            resolveStatus() {

              let labels = {
                'ENABLED': 'Habilitado',
                'DISABLED': 'Desabilitado',
                'NOT_FOUND': 'Não Encontrado'
              }

              return labels[this.status]
            }
        }
    }
</script>
