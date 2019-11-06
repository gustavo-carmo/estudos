<script>
  import FieldFormCommons from './FieldFormCommons.vue'
  import FieldFormPlaceholder from './FieldFormPlaceholder.vue'
  import FieldFormPrefix from './FieldFormPrefix.vue'
  import FieldFormMask from './FieldFormMask.vue'
  import FieldFormRequired from './FieldFormRequired.vue'
  import FieldFormMinValue from './FieldFormMinValue.vue'
  import FieldFormMaxValue from './FieldFormMaxValue.vue'

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
      FieldFormMask,
      FieldFormRequired,
      FieldFormMinValue,
      FieldFormMaxValue
    }
  }
</script>
<template>
  <div>
    <FieldFormCommons :fieldData="fieldData" :rules="rules"></FieldFormCommons>
    <FieldFormPlaceholder :fieldData="fieldData" :rules="rules"></FieldFormPlaceholder>

    <div class="row">
      <FieldFormPrefix :fieldData="fieldData"></FieldFormPrefix>
      <FieldFormMask :fieldData="fieldData"></FieldFormMask>
      <FieldFormRequired :fieldData="fieldData"></FieldFormRequired>
    </div>

    <div class="row">
      <FieldFormMinValue :fieldData="fieldData"></FieldFormMinValue>
      <FieldFormMaxValue :fieldData="fieldData"></FieldFormMaxValue>
    </div>

  </div>
</template>
