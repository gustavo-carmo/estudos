<script>
  export default{
    props: ["type"],
    data(){
      return{
      }
    },
    components:{
    }
  }
</script>
<template>
  <div>

    <!--Básicos-->
    <div v-if="type == 'header'">
      <i class="glyphicon glyphicon-header"></i>
      Header
    </div>
    <div v-if="type == 'text'">
      <i class="glyphicon glyphicon-text-width"></i>
      Text
    </div>
    <div v-if="type == 'numeric'">
      <i class="glyphicon glyphicon-certificate"></i>
      Numeric
    </div>
    <div v-if="type == 'date'">
      <i class="glyphicon glyphicon-calendar"></i>
      Date
    </div>
    <div v-if="type == 'dateTime'">
      <i class="glyphicon glyphicon-calendar"></i>
      Date and Time
    </div>
    <div v-if="type == 'time'">
      <i class="glyphicon glyphicon-time"></i>
      Time
    </div>
    <div v-if="type == 'textArea'">
      <i class="glyphicon glyphicon-text-height"></i>
      Text Area
    </div>
    <div v-if="type == 'select'">
      <i class="glyphicon glyphicon-certificate"></i>
      Combox
    </div>
    <div v-if="type == 'boolean'">
      <i class="glyphicon glyphicon-adjust"></i>
      Boolean
    </div>

    <!--Custom-->
    <div v-if="type == 'cnpj'">
      <i class="glyphicon glyphicon-certificate"></i>
      CNPJ
    </div>
    <div v-if="type == 'cpf'">
      <i class="glyphicon glyphicon-certificate"></i>
      CPF
    </div>
    <div v-if="type == 'currency'">
      <i class="glyphicon glyphicon-usd"></i>
      Currency
    </div>
    <div v-if="type == 'email'">
      <i class="glyphicon glyphicon-envelope"></i>
      E-mail
    </div>

    <!--<div v-if="type == 'multiSelect'">Multi-Select</div>-->
    <!--<div v-if="type == 'upload'">Upload</div>-->
    <!--<div v-if="type == 'password'">Password</div>-->
    <!--<div v-if="type == 'button'">Button</div>-->
  </div>
</template>
<style scoped>
  div{
    margin: 0 2px 2px 0;
  }
  div.el-collapse-item__content {
    padding: 5px;
    }
</style>
