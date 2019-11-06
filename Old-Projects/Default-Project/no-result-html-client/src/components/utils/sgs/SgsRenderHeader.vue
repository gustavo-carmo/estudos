<template>
  <h3>{{ getName() }}</h3>
</template>
<script>
  export default {
    props: ["attr"],
    data(){
        return{
        }
    },
    methods: {
      getName: function() {
        if (this.attr != undefined) {
          if (this.attr.labelPt != undefined && this.attr.labelPt != "") {
            return this.attr.labelPt
          }
        }
        return "Label"
      }
    }
  }

</script>
