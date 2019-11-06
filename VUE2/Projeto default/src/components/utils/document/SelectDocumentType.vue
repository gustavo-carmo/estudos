<script>

    export default {
    props: ["error", "documentType", "placeholder", "disabled"],
    data () {
      return {
        documentTypeValue: '',
        options: [
          { value: "", label: "" },
          { value: "CPF", label: "CPF" },
          { value: "CNPJ", label: "CNPJ" }
        ]
      }
    },
    methods: {
      documentTypeSelected: function (data) {
          this.$emit('selected', data)
      }
    },
    watch: {
      documentType: function (val) {
        let self = this;

        self.documentTypeValue = val
      }
    }
  }

</script>

<template>
  <el-select
      :disabled="disabled"
      :placeholder="placeholder ? placeholder : 'Selecione'"
      @change="documentTypeSelected"
      :class="{ 'has-error' : error }"
      v-model="documentTypeValue"
      filterable
      clearable >
      <el-option
        v-for="item in options"
        :label="item.label"
        :value="item.value" />
  </el-select>
</template>
