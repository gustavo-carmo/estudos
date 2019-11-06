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
          }
        }
    }

</script>
<template>
  <div>
    <el-form-item :label="getLabel()">
      <div class="block">
        <el-date-picker
          v-model="value"
          type="datetime"
          :placeholder="getPlaceholder()">
        </el-date-picker>
      </div>
    </el-form-item>
  </div>
</template>
