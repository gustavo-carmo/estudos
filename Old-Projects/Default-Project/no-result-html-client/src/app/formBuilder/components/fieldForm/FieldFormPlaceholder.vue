<script>
  export default{
    props: ["fieldData", "rules"],
    data() {
      return{
      }
    },
    created() {
    if (this.fieldData.type !== 'cnpj' && this.fieldData.type !== 'cpf'){
        this.rules.placeholderPt = [
          { required: true, message: 'Por favor, preencha o campo', trigger: 'blur' }
        ]
      };
    },
    components: {
    }
  }
</script>
<template>
  <div>
    <div class="row">
      <div class="col-xs-4">
        <el-form-item label="Placeholder Pt" prop="placeholderPt" v-if="fieldData.type != 'cnpj' && fieldData.type != 'cpf'">
          <el-input v-model="fieldData.placeholderPt" auto-complete="off"></el-input>
        </el-form-item>
      </div>
      <div class="col-xs-4">
        <el-form-item label="Placeholder Es" v-if="fieldData.type != 'cnpj' && fieldData.type != 'cpf'">
          <el-input v-model="fieldData.placeholderEs" auto-complete="off"></el-input>
        </el-form-item>
      </div>
      <div class="col-xs-4">
        <el-form-item label="Placeholder En" v-if="fieldData.type != 'cnpj' && fieldData.type != 'cpf'">
          <el-input v-model="fieldData.placeholderEn" auto-complete="off"></el-input>
        </el-form-item>
      </div>
    </div>
  </div>
</template>
