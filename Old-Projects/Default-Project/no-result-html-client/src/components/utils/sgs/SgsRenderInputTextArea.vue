<script>
    export default{
        props: ["attr"],
        data(){
            return{
             textarea: ''
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
        },
        components:{
        }
    }

</script>
<template>
  <div>
    <el-form-item :label="getLabel()">
      <el-input
        :maxlength="getMaxSize()"
        :minlength="getMinSize()"
        :rows="2"
        type="textarea"
        :placeholder="getPlaceholder()"
        v-model="textarea">
      </el-input>
    </el-form-item>
  </div>
</template>
