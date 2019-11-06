<script>
  import {mapActions} from 'vuex';

  import SelectStatus from 'src/components/utils/statusEnabledDisabled/SelectStatus.vue';
  import BootstrapAlertErrors from 'components/utils/BootstrapAlertErrors.vue';
  import SelectTenant from 'src/components/utils/tenant/SelectTenant.vue';

  export default {
      components: {
        SelectStatus,
        BootstrapAlertErrors,
        SelectTenant
      },
      data () {
          return {
              instance: {
                name: '',
                username: '',
                password: '',
                status: '',
                email: '',
                tenant: ''
              },
              errors: [],
              fieldsWithErrors: []
          }
      },
      computed: {
        messageCreateOrUpdate: function () {
          return this.instance.id ? 'atualizado' : 'criado'
        },
        isValidToSubmit: function () {
            let self = this;

            return self.instance.name &&
                self.instance.username &&
                self.instance.status &&
                self.instance.email &&
                (self.instance.password || self.instance.id)
        },
        loginField: () => {
           return [
            { value: "", label: "" },
            { value: "USERNAME", label: "Nome de usuario" },
            { value: "DOCUMENT_NUMBER", label: "Numero de Documento" }
          ]
        }
      },
      mounted () {

        let self = this
        let id = self.$route.params.id

        if (id) {
          self.userGetInstance(id)
            .then(data => {
              self.instance = data
            })
        }
      },
      methods: {
          ...mapActions(['userGetInstance', 'userSave']),

          doSave: function () {
              let self = this;

              self.userSave(self.instance)
                .then(response => {

                  self.$notify({
                    title: 'Successo',
                    message: 'Usuário ' + self.messageCreateOrUpdate + ' com sucesso.',
                    type: 'success'
                  });

                  self.$router.push('/user/show/' + response.id);

                  self.instance = {
                    tenant: ''
                  }
                },
                response => {
                  self.errors = response
                });
          },
          setTenantValue: function (value) {
            this.instance.tenantId = value
          },
          backToSearch: function () {
              let self = this;
              self.$router.push('/user');
          },
          backToShow: function () {
              let self = this;
              let id = self.$route.params.id
              self.$router.push('/user/show/' + id);
          },
          setStatus: function (value) {
            this.instance.status = value
          }
      }
  }
</script>


<template>
  <div>
    <modulePanel>

      <h3 slot="header">{{instance.id ? 'Edição' : 'Novo'}}</h3>

      <div slot="body">
        <bootstrap-alert-errors :errors="errors"></bootstrap-alert-errors>

        <el-form label-position="top">
          <el-row :gutter="10">

            <el-col :span="8">
              <el-form-item label="Nome">
                <el-input
                  id="name"
                  v-model="instance.name"
                  :class="{ 'has-error' : errors.name }"
                />
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="Nome de Usuário">
                <el-input
                  id="username"
                  v-model="instance.username"
                  :class="{ 'has-error' : errors.username }"
                />
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="Senha">
                <el-input
                  id="password"
                  v-model="instance.password"
                  :class="{ 'has-error' : errors.password }"
                />
              </el-form-item>
            </el-col>

          </el-row>

          <el-row :gutter="10">

            <el-col :span="8">
              <el-form-item label="Status">
                <SelectStatus
                  @selected="setStatus"
                  :status="instance.status"
                  :error="errors.status"/>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="Email">
                <el-input
                  id="email"
                  v-model="instance.email"
                  :class="{ 'has-error' : errors.email }"
                />
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="Tenant">
                <!--TODO -  Quando estiver na criação é para exibir apenas os tenants habilitados-->
                <SelectTenant
                  @selected="setTenantValue"
                  :tenantId="instance.tenant.id" />

              </el-form-item>
            </el-col>

          </el-row>

          <el-row :gutter="10">
            <el-col :span="2">
              <el-button
                type="primary"
                size="small"
                @click.prevent="doSave()"
                :disabled="!isValidToSubmit">
                <i class="glyphicon glyphicon-ok" aria-hidden="true" />
                SALVAR
              </el-button>
            </el-col>

            <el-col :span="5">

              <el-button v-if="!instance.id"
                         type="primary"
                         size="small"
                         id="backToSearch"
                         @click.prevent="backToSearch()" >
                <i class="el-icon-arrow-left"></i>
                VOLTAR PARA PESQUISA
              </el-button>

              <el-dropdown v-if="instance.id"

                           split-button
                           type="primary"
                           size="small"
                           id="backToSearch"
                           @click="backToSearch()">
                <i class="el-icon-arrow-left"></i>
                VOLTAR PARA PESQUISA
                <el-dropdown-menu slot="dropdown">
                  <span @click="backToShow()">
                    <el-dropdown-item>VOLTAR PARA DETALHES</el-dropdown-item>
                  </span>
                </el-dropdown-menu>
              </el-dropdown>
            </el-col>
          </el-row>

        </el-form>
      </div>
    </modulePanel>
  </div>
</template>
