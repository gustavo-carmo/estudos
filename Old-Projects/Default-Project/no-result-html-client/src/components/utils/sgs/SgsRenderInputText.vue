<style>
 div.el-input-group__prepend{
    border: 0px;
  }
 div.el-input-group__append{
  border: 0px;
 }
</style>
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
        getMaxSize: function(){
          if (this.attr != undefined) {
            if (this.attr.maxSize != undefined)
              return this.attr.maxSize
          }
        },
        getMinSize: function () {
          if (this.attr != undefined) {
            if (this.attr.minSize != undefined)
              return this.attr.minSize
          }
        }
      }
  }


</script>
<template>
  <el-form-item :label="getLabel()">
    <el-input :placeholder="getPlaceholder()" v-model="input" :maxlength="getMaxSize()" :minlength="getMinSize()">
      <template slot="prepend" v-if="getPrefix()">{{ getPrefix() }}</template>
      <template slot="append" v-if="getSufix()">{{ getSufix() }}</template>
    </el-input>

  </el-form-item>
</template>
