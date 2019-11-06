<script>
  export default{
    props: ["fieldData", "rules"],
    data() {
      return{
      }
      /*name1: [
              { required: true, message: 'Por favor, preencha o campo', trigger: 'blur' }
            ]*/

    },
    created() {
      this.rules.name = [
        { required: true, message: 'Por favor, preencha o campo', trigger: 'blur' }
      ];
      if (this.fieldData.type !== 'header'){
        this.rules.columnName = [
          { required: true, message: 'Por favor, preencha o campo', trigger: 'blur' }
        ]
      };
      if (this.fieldData.type !== 'cnpj' && this.fieldData.type !== 'cpf'){
        this.rules.labelPt = [
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
        <el-form-item label="Nome" prop="name">
          <el-input v-model="fieldData.name" auto-complete="off"></el-input>
        </el-form-item>
      </div>
      <div class="col-xs-4">
        <el-form-item label="Nome da Coluna" prop="columnName" v-if="fieldData.type != 'header'">
          <el-input v-model="fieldData.columnName" auto-complete="off"></el-input>
        </el-form-item>
      </div>
      <div class="col-xs-4">
        <el-form-item label="Persistente" v-if="fieldData.type != 'header'">
          <el-switch v-model="fieldData.persistent" on-text="Sim" off-text="Não" disabled>
          </el-switch>
        </el-form-item>
      </div>
    </div>

    <div class="row">
      <div class="col-xs-4">
        <el-form-item label="Label Pt" prop="labelPt">
          <el-input v-model="fieldData.labelPt" auto-complete="off"></el-input>
        </el-form-item>
      </div>
      <div class="col-xs-4">
        <el-form-item label="Label Es">
          <el-input v-model="fieldData.labelEs" auto-complete="off"></el-input>
        </el-form-item>
      </div>
      <div class="col-xs-4">
        <el-form-item label="Label En">
          <el-input v-model="fieldData.labelEn" auto-complete="off"></el-input>
        </el-form-item>
      </div>
    </div>
  </div>
</template>
