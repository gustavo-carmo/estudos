<script>
export default {
  methods: {
    toggle () {
      this.$emit('toggle')
    },
    data () {
        return {
            title: 'Projeto padrão element-io',
        }
    }
  }
}
</script>

<template>
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" @click="toggle">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand hidden-xs" href="javascript:;">
      <span class="glyphicon glyphicon glyphicon-leaf" aria-hidden="true"></span>
      {{title}}
    </a>
  </div>
</template>
