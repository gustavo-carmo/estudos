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
    <el-form-item label="Prefixo">
      <el-input v-model="fieldData.prefix" auto-complete="off"></el-input>
    </el-form-item>
  </div>
</template>
