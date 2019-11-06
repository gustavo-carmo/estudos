<script>
  import FieldFormCommons from './FieldFormCommons.vue'

  export default{
    props: ["fieldData", "rules"],
    data() {
      return{
      }
    },
    components: {
      FieldFormCommons
    }
  }
</script>
<template>
  <div>
    <FieldFormCommons :fieldData="fieldData" :rules="rules"></FieldFormCommons>
  </div>
</template>
