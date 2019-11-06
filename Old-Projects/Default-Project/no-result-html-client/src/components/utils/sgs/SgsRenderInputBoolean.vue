
<script>
  export default{
      props: ["attr"],
      data(){
          return{
          value: false
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
        },
      }
  }


</script>
<template>
  <el-form-item :label="getLabel()">
    <el-switch on-text="True" off-text="False" v-model="value"></el-switch>
  </el-form-item>
</template>
