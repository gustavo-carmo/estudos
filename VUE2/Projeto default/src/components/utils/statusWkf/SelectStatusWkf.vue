<script>

    export default {
    props: ["status"],
    data () {
      return {
        statusValue: '',
        options: [
          { value: "ENABLED", label: "Habilitado" },
          { value: "DISABLED", label: "Desabilitado" },
          { value: "NOT_FOUND", label: "Não Encontrado" }
        ]
      }
    },
    watch: {
      status: function (val) {
        let self = this;

        self.statusValue = val
      }
    }
  }

</script>

<template>
  <el-select
    v-model="statusValue"
    disabled>
    <el-option
      v-for="item in options"
      :label="item.label"
      :value="item.value" />
  </el-select>
</template>
