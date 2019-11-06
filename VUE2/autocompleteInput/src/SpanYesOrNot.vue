<template>
    <span class="label"  :class="{ 'label-success' : value == 'YES', 'label-danger' : value == 'NOT' }">
        {{resolveStatus()}}
    </span>
</template>

<script>
    export default{
        props: ["value"],
        methods: {
            resolveStatus() {

              let labels = {
                'YES': 'Sim',
                'NOT': 'Não'
              }

              return labels[this.value]
            }
        }
    }
</script>
