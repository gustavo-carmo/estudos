<script>
  import FieldFormCommons from './FieldFormCommons.vue'
  export default{
    props: ["fieldData", "rules"],
    data() {
      return {
      }
    },
    components: {
      FieldFormCommons
    }
  }
</script>
<template>
  <FieldFormCommons :fieldData="fieldData" :rules="rules"></FieldFormCommons>
</template>
