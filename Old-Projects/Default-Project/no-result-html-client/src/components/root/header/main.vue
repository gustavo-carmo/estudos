<script>
  import Brand from './brand'
  import MenuUser from './menuUser'
  import MenuApp from './menuApp'
  import {mapGetters} from 'vuex';

  export default {
    name: 'pa-header',
    components: {Brand, MenuApp, MenuUser},
    data () {
      return {
        isOpen: false,
        isOpenUser: false
      }
    },
    methods: {
      onToggleMenu () {
        this.isOpen = !this.isOpen
      },
      toggleUserMenu () {
        this.isOpenUser = !this.isOpenUser
      },
    },
    computed: {
      ...mapGetters(['isLogged'])
    }
  }


</script>
<style>

.aux-text{
  display: inline-block !important;
}
.top-header{
  border-bottom: 1px solid #C8C8C8;
}
.border-sgs {
  border-bottom: 1px solid #FF6600;
  border-bottom-width: 5px;
  height: 80px;
}
.navbar-nav > li > a {
  padding-top: 23.5px;
  padding-bottom: 25.5px;
  padding-bottom: 29.5px;
  color: black;
  background-color: #fff;
}
.navbar-nav > li > a:hover {
  background-color: #636363;
  color: #fff
}
.nav .open > a, .nav .open > a:hover, .nav .open > a:focus {
  background-color: #636363;
  color: #fff
}
.navbar-nav > li > .dropdown-menu > li > a:hover {
  background-color: red;
  color: #fff;
}
.dropdown-menu > li {
  border-bottom: 1px solid #e0eded;
}.dropdown-menu {
  border-top: 1px solid #e91b23;
  border-bottom: 3px solid #e91b23;
}
.navbar {
  margin-bottom: 0;
}


</style>
<template>
  <header>
    <div class="top-header">
      <div class="container">
        <div class="row">
          <div class="col-xs-12">
            <div class="pull-right">
              <MenuUser/>
            </div>
            <div>
              <span class="aux-text hidden-xs">
                SGS Now - Builder
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <nav class="navbar border-sgs">
      <div class="container">
        <Brand @toggle="onToggleMenu"/>
        <div class="collapse navbar-collapse" :class="{ in: isOpen }"  v-if="isLogged">

          <ul class="nav navbar-nav navbar-right">

            <router-link :to="{ name: 'BusinessLineList' }" tag="li" exact>
              <a>Business Line</a>
            </router-link>
            <router-link :to="{ name: 'ContractList' }" tag="li" exact>
              <a>Contratos</a>
            </router-link>
            <router-link :to="{ name: 'FormList' }" tag="li" exact>
              <a>Forms</a>
            </router-link>
            <router-link :to="{ name: 'MenuList' }" tag="li" exact>
              <a>Menu</a>
            </router-link>

            <!--<li
              class="dropdown"
              @click="toggleUserMenu"
              :class="{ open: isOpenUser }"
            >
              <a href="javascript:;" class="dropdown-toggle">
                Gereciar
              </a>
              <ul class="dropdown-menu">
                <router-link :to="{ name: 'BusinessLineList' }" tag="li" exact>
                  <a>Business Line</a>
                </router-link>
                <router-link :to="{ name: 'ContractList' }" tag="li" exact>
                  <a>Contracts</a>
                </router-link>
                <router-link :to="{ name: 'FormList' }" tag="li" exact>
                  <a>Forms</a>
                </router-link>
                <router-link :to="{ name: 'MenuList' }" tag="li" exact>
                  <a>Menu</a>
                </router-link>
              </ul>
            </li>-->
          </ul>

        </div>
      </div>
    </nav>
  </header>
</template>

