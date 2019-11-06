<script>
    import SgsInput from './SgsRenderInput.vue'

    export default{
      props: ["type"],
      data(){
          return{
          }
      },
      components:{
          SgsInput
      }
    }
</script>



<template>
  <div>
    <SgsInput v-if="type == 'text'"></SgsInput>
  </div>
</template>
