<script>
  import FieldFormCommons from './FieldFormCommons.vue'
  import FieldFormPrefix from './FieldFormPrefix.vue'
  import FieldFormSufix from './FieldFormSufix.vue'
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
      FieldFormPrefix,
      FieldFormSufix,
      FieldFormRequired,
      FieldFormMinValue,
      FieldFormMaxValue
    }
  }
</script>
<template>
  <div>
    <FieldFormCommons :fieldData="fieldData" :rules="rules"></FieldFormCommons>

    <div class="row">
      <FieldFormPrefix :fieldData="fieldData"></FieldFormPrefix>
      <FieldFormSufix :fieldData="fieldData"></FieldFormSufix>
      <FieldFormRequired :fieldData="fieldData"></FieldFormRequired>
    </div>

    <div class="row">
      <FieldFormMinValue :fieldData="fieldData"></FieldFormMinValue>
      <FieldFormMaxValue :fieldData="fieldData"></FieldFormMaxValue>
    </div>

  </div>
</template>
