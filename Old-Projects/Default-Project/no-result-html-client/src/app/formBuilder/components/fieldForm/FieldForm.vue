<script>
  import FieldFormHeader from './FieldFormHeader.vue'
  import FieldFormText from './FieldFormText.vue'
  import FieldFormNumeric from './FieldFormNumeric.vue'
  import FieldFormDate from './FieldFormDate.vue'
  import FieldFormDateTime from './FieldFormDateTime.vue'
  import FieldFormTime from './FieldFormTime.vue'
  import FieldFormTextArea from './FieldFormTextArea.vue'
  import FieldFormCnpj from './FieldFormCnpj.vue'
  import FieldFormCpf from './FieldFormCpf.vue'
  import FieldFormCurrency from './FieldFormCurrency.vue'
  import FieldFormEmail from './FieldFormEmail.vue'
  import FieldFormBoolean from './FieldFormBoolean.vue'

  export default{
    props: ["type", "fieldData", "rules"],
    data() {
      return {
      }
    },
    created() {
      Object.assign(this.rules, {});
    },
    components: {
      FieldFormHeader,
      FieldFormText,
      FieldFormNumeric,
      FieldFormDate,
      FieldFormDateTime,
      FieldFormTime,
      FieldFormTextArea,
      FieldFormCnpj,
      FieldFormCpf,
      FieldFormCurrency,
      FieldFormEmail,
      FieldFormBoolean
    }
  }
</script>
<template>
  <div>
    <FieldFormHeader v-if="type == 'header'" :fieldData="fieldData" :rules="rules"></FieldFormHeader>
    <FieldFormText v-if="type == 'text'" :fieldData="fieldData" :rules="rules"></FieldFormText>
    <FieldFormNumeric v-if="type == 'numeric'" :fieldData="fieldData" :rules="rules"></FieldFormNumeric>
    <FieldFormDate v-if="type == 'date'" :fieldData="fieldData" :rules="rules"></FieldFormDate>
    <FieldFormDateTime v-if="type == 'dateTime'" :fieldData="fieldData" :rules="rules"></FieldFormDateTime>
    <FieldFormTime v-if="type == 'time'" :fieldData="fieldData" :rules="rules"></FieldFormTime>
    <FieldFormTextArea v-if="type == 'textArea'" :fieldData="fieldData" :rules="rules"></FieldFormTextArea>
    <FieldFormCnpj v-if="type == 'cnpj'" :fieldData="fieldData" :rules="rules"></FieldFormCnpj>
    <FieldFormCpf v-if="type == 'cpf'" :fieldData="fieldData" :rules="rules"></FieldFormCpf>
    <FieldFormCurrency v-if="type == 'currency'" :fieldData="fieldData" :rules="rules"></FieldFormCurrency>
    <FieldFormEmail v-if="type == 'email'" :fieldData="fieldData" :rules="rules"></FieldFormEmail>
    <FieldFormBoolean v-if="type == 'boolean'" :fieldData="fieldData" :rules="rules"></FieldFormBoolean>
  </div>
</template>
