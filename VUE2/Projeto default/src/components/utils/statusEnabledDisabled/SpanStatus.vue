<template>
    <span class="label"  :class="{ 'label-success' : status == 'ENABLED', 'label-danger' : status == 'DISABLED' }">
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
                'DISABLED': 'Desabilitado'
              }

              return labels[this.status]
            }
        }
    }
</script>
