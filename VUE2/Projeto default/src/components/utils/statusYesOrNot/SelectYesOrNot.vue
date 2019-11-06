<script>

    export default {
    props: ["status"],
    data () {
      return {
        options: [
          { value: "YES", label: "Sim" },
          { value: "NOT", label: "Não" }
        ]
      }
    },
    methods: {
      statusSelected: function (data) {
          this.$emit('selected', data)
      }
    }
  }

</script>

<template>
  <el-select
      disabled
      v-model="status">
      <el-option
        v-for="item in options"
        :label="item.label"
        :value="item.value" />
  </el-select>
</template>
