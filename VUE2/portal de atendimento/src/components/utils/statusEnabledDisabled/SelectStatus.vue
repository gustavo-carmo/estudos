<script>

    export default {
    props: ["error", "status", "placeholder","disableField"],
    data () {
      return {
        statusValue: '',
        options: [
          { value: "", label: "" },
          { value: "ENABLED", label: "Habilitado" },
          { value: "DISABLED", label: "Desabilitado" }
        ]
      }
    },
    methods: {
      statusSelected: function (data) {
          this.$emit('selected', data)
      }
    },
    watch: {
      status: function (val) {
        let self = this;

        self.statusValue = val
      }
    },
    mounted () {

      let self = this;

      if (self.status) {
        self.statusValue = self.status
      }
    }
  }

</script>

<template>
  <el-select
      :placeholder="placeholder ? placeholder : 'Selecione'"
      :disabled="disableField"
      @change="statusSelected"
      :class="{ 'has-error' : error }"
      v-model="statusValue">
      <el-option
        v-for="item in options"
        :label="item.label"
        :value="item.value" />
  </el-select>
</template>
