<script>
  import {mapActions, mapGetters} from 'vuex';

  export default {
    data () {
      return {
        isOpenUser: false
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
  <ul class="nav navbar-nav navbar-right" v-if="isLogged">
    <li
      class="dropdown"
      @click="toggleUserMenu"
      :class="{ open: isOpenUser }"
    >
      <a href="javascript:;" class="dropdown-toggle">
        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
        {{ loggedUser.username }}
        <span class="caret"></span>
      </a>
      <ul class="dropdown-menu">
        <li><a href="#" @click.prevent="doLogout">Sair</a></li>
        <!--<li><a href="#">Another action</a></li>
        <li><a href="#">Something else here</a></li>
        <li role="separator" class="divider"></li>
        <li><a href="#">Separated link</a></li>-->
      </ul>
    </li>
  </ul>
</template>
