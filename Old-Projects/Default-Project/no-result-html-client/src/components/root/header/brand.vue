<script>
export default {
  methods: {
    toggle () {
      this.$emit('toggle')
    },
    data () {
      return {
      }
    }
  }
}
</script>

<template>
  <div class="navbar-header">
      <img src="./logo.png" alt="SGS DO BRASIL" class="padding-top-10px">
  </div>
</template>
<style>
 .padding-top-10px {
    padding-top: 10px;
}
</style>
