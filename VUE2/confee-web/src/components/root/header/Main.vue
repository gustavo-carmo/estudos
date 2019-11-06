<template>
  <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
      <Brand @toggle="onToggleMenu"/>

      <div class="collapse navbar-collapse" :class="{ in : isOpen }" id="bs-example-navbar-collapse-1">

        <Left />
        <Right />

      </div>
    </div>
  </nav>
</template>

<script>
  import Brand from './Brand'
  import Left from './Left'
  import Right from './Right'

  export default {
    name: 'confee-header',
    components: {
      Brand,
      Left,
      Right
    },
    data () {
      return { isOpen: false }
    },
    methods: {
      onToggleMenu () {
        this.isOpen = !this.isOpen
      }
    }
  }
</script>
