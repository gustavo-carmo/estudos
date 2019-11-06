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
        },

        getPlaceholder: function (){
          if (this.attr != undefined){
            if(this.attr.placeholderPt != undefined && this.attr.placeholderPt != ""){
              return this.attr.placeholderPt
            }
          }
          return "Placeholder"
        },

        getMaxValue: function(){
          if (this.attr != undefined) {
            if (this.attr.maxValue != undefined)
              return this.attr.maxValue
          }
        },

        getMinValue: function () {
          if (this.attr != undefined) {
            if (this.attr.minValue != undefined)
              return this.attr.minValue
          }
        }
      }
  }


</script>
<template>
  <el-form-item :label="getLabel()">
    <el-input :placeholder="getPlaceholder()" v-model="input" :maxlength="getMaxValue()" :minlength="getMinValue()">
      <template slot="prepend">$</template>
    </el-input>

  </el-form-item>
</template>
