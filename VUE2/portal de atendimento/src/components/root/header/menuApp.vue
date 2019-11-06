<script>

  import {mapGetters} from 'vuex';

  export default {
    computed: {
      ...mapGetters([
      'loggedUser',
      'isLogged',
      'authMenuMainServiceOrder',
      'authMenuMainContractor',
      'authMenuMainCustomer',
      'authMenuMainTenant',
      'authMenuMainUser'
      ])
    }
  }
</script>

<template>
  <ul class="nav navbar-nav">
    <router-link :to="{ name: 'ServiceOrderList' }" tag="li" exact v-show="authMenuMainServiceOrder">
      <a>Ordem de Serviço</a>
    </router-link>
    <router-link :to="{ name: 'TenantList' }" tag="li" exact v-show="authMenuMainTenant">
      <a>Tenant</a>
    </router-link>
    <router-link :to="{ name: 'ContractorList' }" tag="li" exact v-show="authMenuMainContractor">
      <a>Contratante</a>
    </router-link>
    <router-link :to="{ name: 'CustomerList' }" tag="li" exact v-show="authMenuMainCustomer">
      <a>Cliente</a>
    </router-link>
    <router-link :to="{ name: 'UserList' }" tag="li" exact v-show="authMenuMainUser">
      <a>Usuário</a>
    </router-link>
    <router-link :to="{ name: 'BusinessAreaList'}" tag="li" exact>
      <a>Areas de Negocio</a>
    </router-link>
  </ul>
</template>

