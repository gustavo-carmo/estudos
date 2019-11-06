<script>
  import FieldFormCommons from './FieldFormCommons.vue'
  import FieldFormPlaceholder from './FieldFormPlaceholder.vue'
  import FieldFormPrefix from './FieldFormPrefix.vue'
  import FieldFormSufix from './FieldFormSufix.vue'
  import FieldFormMask from './FieldFormMask.vue'
  import FieldFormRequired from './FieldFormRequired.vue'
  import FieldFormMinSize from './FieldFormMinSize.vue'
  import FieldFormMaxSize from './FieldFormMaxSize.vue'
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
      FieldFormSufix,
      FieldFormMask,
      FieldFormRequired,
      FieldFormMinSize,
      FieldFormMaxSize,
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
      <FieldFormSufix :fieldData="fieldData"></FieldFormSufix>
      <FieldFormMask :fieldData="fieldData"></FieldFormMask>
    </div>

    <div class="row">
      <FieldFormRequired :fieldData="fieldData"></FieldFormRequired>
      <FieldFormMinSize :fieldData="fieldData"></FieldFormMinSize>
      <FieldFormMaxSize :fieldData="fieldData"></FieldFormMaxSize>
    </div>

    <div class="row">
      <FieldFormRegex :fieldData="fieldData"></FieldFormRegex>
    </div>

  </div>
</template>
