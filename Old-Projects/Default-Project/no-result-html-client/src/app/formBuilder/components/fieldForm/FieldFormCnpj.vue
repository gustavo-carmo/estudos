<script>
  import FieldFormCommons from './FieldFormCommons.vue'
  import FieldFormPlaceholder from './FieldFormPlaceholder.vue'
  import FieldFormMask from './FieldFormMask.vue'
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
      FieldFormMask,
      FieldFormRequired
    }
  }
</script>
<template>
  <div>
    <FieldFormCommons :fieldData="fieldData"  :rules="rules"></FieldFormCommons>
    <FieldFormPlaceholder :fieldData="fieldData"  :rules="rules"></FieldFormPlaceholder>

    <div class="row">
      <FieldFormMask :fieldData="fieldData"></FieldFormMask>
      <FieldFormRequired :fieldData="fieldData"></FieldFormRequired>
    </div>
  </div>
</template>
