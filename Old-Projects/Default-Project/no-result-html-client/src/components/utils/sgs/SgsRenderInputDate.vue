<script>
    export default{
        props: ["attr"],
        data(){
            return{
            value: ''
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
          getPlaceholder: function (){
            if (this.attr != undefined){
              if(this.attr.placeholderPt != undefined && this.attr.placeholderPt != ""){
                return this.attr.placeholderPt
              }
            }
            return "Placeholder"
          },
        }
    }
</script>
<template>
  <el-form-item :label="getLabel()">
    <el-date-picker v-model="value" :placeholder="getPlaceholder()">
    </el-date-picker>
  </el-form-item>
</template>
