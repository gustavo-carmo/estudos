<script>
  export default{
    props: ["fieldData"],
    data() {
      return{
      }
    },
    components: {
    }
  }
</script>
<template>
  <div class="col-xs-4">
    <el-form-item label="Valor Máximo">
      <el-input-number v-model="fieldData.maxValue" :min="0"></el-input-number>
    </el-form-item>
  </div>
</template>
