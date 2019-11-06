<script>
  import {mapActions, mapGetters} from 'vuex';

  export default {
    data () {
      return {
      }
    },
    computed: {
      ...mapGetters(['loggedUser', 'isLogged'])
    },
    methods: {
      ...mapActions(['logout']),
      toggleUserMenu () {
        this.isOpenUser = !this.isOpenUser
      },
      doLogout () {
        this.logout()
        this.$router.push({path: '/auth'})
      }
    }
  }


</script>

<template>
  <div v-if="isLogged">
      <a href="#" @click.prevent="doLogout" class="padding-right-20px">Sair</a>
      <span>Bem vindo, <b>{{ loggedUser.username }}</b></span>
  </div>
</template>
<style>
  .padding-right-20px{
    padding-right: 20px;
}
</style>
