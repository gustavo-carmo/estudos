<script>
  import FieldFormCommons from './FieldFormCommons.vue'
  import FieldFormPlaceholder from './FieldFormPlaceholder.vue'
  import FieldFormPrefix from './FieldFormPrefix.vue'
  import FieldFormRequired from './FieldFormRequired.vue'
  import FieldFormRegex from './FieldFormRegex.vue'

  export default{
    props: ["fieldData", "rules"],
    data() {
      return{
      }
    },
    components: {
      FieldFormCommons,
      FieldFormPlaceholder,
      FieldFormPrefix,
      FieldFormRequired,
      FieldFormRegex
    }
  }
</script>
<template>
  <div>
    <FieldFormCommons :fieldData="fieldData" :rules="rules"></FieldFormCommons>
    <FieldFormPlaceholder :fieldData="fieldData" :rules="rules"></FieldFormPlaceholder>

    <div class="row">
      <FieldFormPrefix :fieldData="fieldData"></FieldFormPrefix>
      <FieldFormRequired :fieldData="fieldData"></FieldFormRequired>
      <FieldFormRegex :fieldData="fieldData"></FieldFormRegex>
    </div>

  </div>
</template>
