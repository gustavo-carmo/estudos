<template>
    <span class="label"  :class="{ 'label-success' : status == 'START', 'label-info' : status == 'CLOSED', 'label-danger' : status == 'CANCELED', 'label-warning' : status == 'IN_PROGRESS'}">
        {{resolveStatus}}
    </span>
</template>

<script>
    export default{
        props: ["status"],
        computed: {

          resolveStatus() {

            let labels = {
              'START': 'Aberta',
              'CLOSED': 'Fechada',
              'CANCELED': 'Cancelada',
              'IN_PROGRESS': 'Encaminhada'
            }

            return labels[this.status]
          }
        }
    }
</script>
