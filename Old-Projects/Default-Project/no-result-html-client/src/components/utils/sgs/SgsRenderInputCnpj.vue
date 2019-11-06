<script>
    export default{
        props: ["attr"],
        data(){
            return{
            }
        },
        methods: {
        getLabel: function (){
          if (this.attr != undefined){
            if (this.attr.labelPt != undefined && this.attr.labelPt != ""){
              return this.attr.labelPt
            }
          }
          return "Label"
        }
      }
    }
</script>
<template>
  <div>
    <el-form-item label="CNPJ">
      <el-input placeholder="99.999.999/9999-99" v-model="input">
      </el-input>
    </el-form-item>
  </div>
</template>
