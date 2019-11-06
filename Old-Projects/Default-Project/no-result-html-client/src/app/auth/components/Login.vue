<script>

    import {mapActions} from 'vuex';

    import ModuleTemplate from 'src/components/root/main/ModuleTemplate.vue';
    import initializationService from 'src/services/InitializationService.js';
    import authService from 'src/services/AuthService';

    export default {
        components: {
            ModuleTemplate
        },
        data () {
            return {
                title: 'Tela de Login',
                loginField: '',
                username: '',
                password: '',
                tenantIdentifier: '',
                error: ''
            }
        },
        mounted () {
             // this.doInitialization();
        },
        computed: {
            isValidToSubmit: function () {
                let self = this;
                return self.username && self.password
            },
            loginPlaceholder: function() {
              let self = this;
              if (self.loginField === 'DOCUMENT_NUMBER') {
                return "Número de Documento";
              } else {
                return "Nome de Usuário";
              }
            }
        },
        methods: {
            ...mapActions(['login']),
            doLogin: function () {

              let self = this;
              self.$loader.show()
              self.error = '';

              this.login({
                tenantIdentifier: self.tenantIdentifier,
                username: self.username,
                password: self.password
                })
                .then(response => {

                  self.$loader.hide();

                  if ((response.roles.indexOf('ROLE_SERVICE_ORDER')) > -1) {
                    self.$router.push({ path: "/service-order" });
                  } else {
                    self.$router.push({ path: "/" });
                  }
                }).catch((err) => {
                  self.error = self.loginPlaceholder + '/Senha inválida';
                  self.$loader.hide()
                })
            }
        }
    }

</script>

<style>
.padding-top-50px{
  padding-top: 50px;
}
</style>

<template>
  <!--TODO - ALTERAR PARA CODIGO DO COMPONENTE ELEMENT-->
  <div class="container">
    <div class="row padding-top-50px">

      <div class="col-md-offset-4 col-md-4">

        <div class="panel panel-default">
          <div class="panel-body">

            <div class="text-center">
              <img src="./logo.png" alt="SGS DO BRASIL">
            </div>

            <div class="padding-bottom-50"></div>

            <div class="alert alert-mini alert-danger margin-bottom-30" v-if="error">
              <strong>Error!</strong> {{error}}
            </div>

            <form autocomplete="off">
              <fieldset>

                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input type="text"
                           name="username"
                           class="form-control"
                           :placeholder="loginPlaceholder"
                           required=""
                           v-model="username">
                  </div>
                </div>

                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-asterisk"></i></span>
                    <input type="password" name="password" class="form-control" placeholder="Senha" required=""
                           v-model="password">
                  </div>
                </div>

                <div class="padding-bottom-50"></div>

                <!--<button class="btn btn-primary" @click.prevent="doLogin()" :disabled="!isValidToSubmit">
                  Entrar
                </button>-->

                <el-button type="primary" size="menium" @click.prevent="doLogin()" :disabled="!isValidToSubmit">
                  Entrar
                </el-button>

              </fieldset>
            </form>

            <div class="padding-bottom-50"></div>

          </div>
        </div>
      </div>

    </div>

  </div>
</template>

