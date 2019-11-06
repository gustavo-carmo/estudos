<script>
    import SgsRenderHeader from './SgsRenderHeader.vue'
    import SgsRenderInputText from './SgsRenderInputText.vue'
    import SgsRenderInputNumber from './SgsRenderInputNumber.vue'
    import SgsRenderInputDate from './SgsRenderInputDate.vue'
    import SgsRenderInputDateTime from './SgsRenderInputDateTime.vue'
    import SgsRenderInputTime from './SgsRenderInputTime.vue'
    import SgsRenderInputSelect from './SgsRenderInputSelect.vue'
    import SgsRenderInputMultiSelect from './SgsRenderInputMultiSelect'
    import SgsRenderInputTextArea from './SgsRenderInputTextArea'
    import SgsRenderInputButtonUpload from './SgsRenderInputButtonUpload'
    import SgsRenderInputPassword from './SgsRenderInputPassword'
    import SgsRenderInputButton from './SgsRenderInputButton'
    import SgsRenderInputCpf from './SgsRenderInputCpf'
    import SgsRenderInputCnpj from './SgsRenderInputCnpj'
    import SgsRenderInputEmail from './SgsRenderInputEmail'
    import SgsRenderInputCurrency from './SgsRenderInputCurrency'
    import SgsRenderInputBoolean from './SgsRenderInputBoolean'

    export default{
      props: ["type", "attr"],
      data(){
          return{
          }
      },
      components:{
        SgsRenderHeader,
        SgsRenderInputText,
        SgsRenderInputNumber,
        SgsRenderInputDate,
        SgsRenderInputDateTime,
        SgsRenderInputTime,
        SgsRenderInputSelect,
        SgsRenderInputMultiSelect,
        SgsRenderInputTextArea,
        SgsRenderInputButtonUpload,
        SgsRenderInputPassword,
        SgsRenderInputButton,
        SgsRenderInputCpf,
        SgsRenderInputCnpj,
        SgsRenderInputEmail,
        SgsRenderInputCurrency,
        SgsRenderInputBoolean
      }
    }
</script>



<template>
  <div>
    <SgsRenderHeader v-if="type == 'header'" :attr="attr"></SgsRenderHeader>
    <SgsRenderInputText v-if="type == 'text'" :attr="attr"></SgsRenderInputText>
    <SgsRenderInputNumber v-if="type == 'numeric'" :attr="attr"></SgsRenderInputNumber>
    <SgsRenderInputDate v-if="type == 'date'" :attr="attr"></SgsRenderInputDate>
    <SgsRenderInputDateTime v-if="type == 'dateTime'" :attr="attr"></SgsRenderInputDateTime>
    <SgsRenderInputTime v-if="type == 'time'" :attr="attr"></SgsRenderInputTime>
    <SgsRenderInputSelect v-if="type == 'select'"></SgsRenderInputSelect>
    <SgsRenderInputMultiSelect v-if="type == 'multiSelect'"></SgsRenderInputMultiSelect>
    <SgsRenderInputTextArea v-if="type == 'textArea'" :attr="attr"></SgsRenderInputTextArea>
    <SgsRenderInputButtonUpload v-if="type == 'upload'"></SgsRenderInputButtonUpload>
    <SgsRenderInputPassword v-if="type == 'password'" :attr="attr"></SgsRenderInputPassword>
    <SgsRenderInputButton v-if="type == 'button'"></SgsRenderInputButton>
    <SgsRenderInputCpf v-if="type == 'cpf'"></SgsRenderInputCpf>
    <SgsRenderInputCnpj v-if="type == 'cnpj'" :attr="attr"></SgsRenderInputCnpj>
    <SgsRenderInputEmail v-if="type == 'email'" :attr="attr"></SgsRenderInputEmail>
    <SgsRenderInputCurrency v-if="type == 'currency'" :attr="attr"></SgsRenderInputCurrency>
    <SgsRenderInputBoolean v-if="type == 'boolean'" :attr="attr"></SgsRenderInputBoolean>
  </div>
</template>
