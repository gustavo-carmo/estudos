<script>
    export default{
        props: ["attr"],
        data(){
            return{
              num: 1
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
            },

            getPrefix: function(){
              if (this.attr != undefined) {
                if (this.attr.prefix != undefined && this.attr.prefix != ""){
                  return this.attr.prefix;
                }
              }
            },

            getSufix: function(){
              if (this.attr != undefined) {
                if (this.attr.sufix != undefined && this.attr.sufix != "")
                  return this.attr.sufix;
              }
            },
          }
    }

</script>
<template>
  <div>
    <el-form-item :label="getLabel()">
      <el-input-number v-model="num" :min="getMinValue()" :max="getMaxValue()">
        <template slot="prepend" v-if="getPrefix()">{{ getPrefix() }}</template>
        <template slot="append" v-if="getSufix()">{{ getSufix() }}</template>
      </el-input-number>
    </el-form-item>
  </div>
</template>
<style>
  div.el-form-item > .el-form-item__content .el-input-number span{
      display: none;
}
</style>
