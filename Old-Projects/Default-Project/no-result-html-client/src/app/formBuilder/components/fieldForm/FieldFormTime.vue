<script>
  import FieldFormCommons from './FieldFormCommons.vue'
  import FieldFormPlaceholder from './FieldFormPlaceholder.vue'
  import FieldFormRequired from './FieldFormRequired.vue'

  export default{
    props: ["fieldData", "rules"],
    data() {
      return{
      }
    },
    components: {
      FieldFormCommons,
      FieldFormPlaceholder,
      FieldFormRequired
    }
  }
</script>
<template>
  <div>
    <FieldFormCommons :fieldData="fieldData" :rules="rules"></FieldFormCommons>
    <FieldFormPlaceholder :fieldData="fieldData" :rules="rules"></FieldFormPlaceholder>

    <div class="row">
      <FieldFormRequired :fieldData="fieldData"></FieldFormRequired>
    </div>

  </div>
</template>
