<template>
  <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">

      <div class="navbar-header">
        <button type="button" class="navbar-toggle">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>

        <router-link :to="{ name: 'dashboard' }" class="navbar-brand">Confee</router-link>

      </div>

      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

        <ul class="nav navbar-nav">
          <router-link :to="{ name: 'dashboard' }" tag="li" exact>
            <a>Home</a>
          </router-link>
          <router-link :to="{ name: 'auth' }" tag="li">
            <a>Entrar</a>
          </router-link>
        </ul>
      </div>
    </div>

  </nav>
</template>

<script>
  export default {
  }
</script>
