<script>
  import Brand from './brand'
  import MenuUser from './menuUser'
  import MenuApp from './menuApp'
  import {mapGetters} from 'vuex';

  export default {
    name: 'pa-header',
    components: {Brand, MenuApp, MenuUser},
    data () {
      return {isOpen: false}
    },
    methods: {
      onToggleMenu () {
        this.isOpen = !this.isOpen
      }
    },
    computed: {
      ...mapGetters(['isLogged'])
    }
  }
</script>

<template>
  <header v-show="isLogged">
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">

        <Brand @toggle="onToggleMenu"/>

        <div class="collapse navbar-collapse" :class="{ in: isOpen }">
          <MenuUser/>
          <MenuApp/>
        </div>

      </div>
    </nav>
  </header>
</template>
