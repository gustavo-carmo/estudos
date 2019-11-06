<template>
  <ul class="nav navbar-nav navbar-right">
    <router-link :to="{ name: 'auth' }" tag="li" v-if="!isLogged">
      <a>Entrar</a>
    </router-link>
    <li class="dropdown" :class="{ open : isOpenUser}" v-if="isLogged" @click="ToggleUserMenu">
      <a href="javascript:;" class="dropdown-menu">{{currentUser.name}}</a>
      <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
        <li><a href="javascript:;">Action</a></li>
        <li><a href="javascript:;">Another action</a></li>
        <li><a href="javascript:;">Something else here</a></li>
        <li role="separator" class="divider"></li>
        <li><a href="javascript:;">Separated link</a></li>
      </ul>
    </li>
  </ul>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  data () {
    return {
      isOpenUser: false
    }
  },
  computed: {
    ...mapGetters(['isLogged', 'currentUser'])
  },
  methods: {
    ToggleUserMenu () {
      this.isOpenUser = !this.isOpenUser
    }
  }
}
</script>
