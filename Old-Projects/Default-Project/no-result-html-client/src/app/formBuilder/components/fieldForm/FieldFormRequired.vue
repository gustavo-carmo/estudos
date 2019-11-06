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
    <el-form-item label="Requerido">
      <el-switch v-model="fieldData.required" on-text="Sim" off-text="Não"></el-switch>
    </el-form-item>
  </div>
</template>
