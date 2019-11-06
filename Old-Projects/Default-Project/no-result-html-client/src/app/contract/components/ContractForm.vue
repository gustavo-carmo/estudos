<script>

  import {mapActions, mapGetters} from 'vuex';

  import FooterCreateEdit from 'components/utils/footerCrud/FooterCreateEdit.vue';
  import BootstrapAlertErrors from 'components/utils/BootstrapAlertErrors.vue';

  export default{

      components: {
        FooterCreateEdit,
        BootstrapAlertErrors
      },
      data(){
        return{
          instance: {
            id: '',
            businessLine: '',
            name: '',
            labelPt: '',
            labelEs: '',
            labelEn: '',
            code: '',
            databaseName: '',
            databaseUser: '',
            databasePass: '',
            databaseHost: '',
            databasePort: '',
            databaseExtra: '',
            databaseDriver: 'MSSQL'
          },
          errors: [],
          fieldsWithErrors: [],
        }
      },
      computed: {
        ...mapGetters({
          businessLineList: 'businessLineList'
        }),
        messageCreateOrUpdate: function () {
          return this.instance.id ? 'atualizado' : 'criado'
        },
        isValidToSubmit: function () {
            let self = this;

            return self.instance.businessLine &&
                self.instance.name &&
                self.instance.labelPt &&
                self.instance.code &&
                self.instance.databaseName &&
                self.instance.databaseUser &&
                self.instance.databasePass &&
                self.instance.databaseHost &&
                self.instance.databasePort
        },
      },
      mounted () {

        let self = this
        let id = self.$route.params.id

        self.businessLineSearch();

        if (id) {
          self.contractGetInstance(id)
            .then(data => {

              self.instance.id = data.id;
              self.instance.name = data.name;
              self.instance.labelPt = data.labelPt;
              self.instance.labelEs = data.labelEs;
              self.instance.labelEn = data.labelEn;
              self.instance.code = data.code;
              self.instance.databaseName = data.databaseName;
              self.instance.databaseUser = data.databaseUser;
              self.instance.databasePass = data.databasePass;
              self.instance.databaseHost = data.databaseHost;
              self.instance.databasePort = data.databasePort;
              self.instance.databaseExtra = data.databaseExtra;
              self.instance.businessLine = data.businessLine.id;
            });
        }
      },
      methods: {
          ...mapActions([
            'contractGetInstance',
            'contractSave',
            'showLoader',
            'hideLoader',
            'businessLineSearch'
            ]),

          doSave: function () {
            let self = this;

            self.showLoader();
            self.contractSave(self.instance)
              .then(response => {

                self.hideLoader();

                self.$notify({
                  title: 'Sucesso',
                  message: 'Contrato ' + self.messageCreateOrUpdate + ' com sucesso.',
                  type: 'success'
                });

                self.$router.push('/contract/show/' + response.id);

                self.instance = {}
              },
              response => {

                self.hideLoader();

                self.errors = response;
              });
          },
          backToSearch: function () {
              let self = this;
              self.$router.push('/contract');
          },
          backToShow: function () {
              let self = this;

              self.$router.push('/contract/show/' + self.$route.params.id);
          }
      }
  }
</script>

<template>
  <modulePanel>
    <div slot="body">
      <bootstrap-alert-errors :errors="errors"></bootstrap-alert-errors>

      <el-form label-position="top">
        <el-row :gutter="10">

          <el-col :span="8">
            <el-form-item label="Código">
              <el-input
                id="code"
                v-model="instance.code"
                :class="{ 'has-error' : errors.code }"
              />
            </el-form-item>
          </el-col>

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
            <el-form-item label="Business Line">

              <el-select
                :placeholder="'Selecione'"
                :class="{ 'has-error' : errors.businessLine }"
                v-model="instance.businessLine">
                <el-option
                  v-for="item in businessLineList"
                  :label="item.name"
                  :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row :gutter="10">

          <el-col :span="8">
            <el-form-item label="Label em Português">
              <el-input
                id="labelPt"
                v-model="instance.labelPt"
                :class="{ 'has-error' : errors.labelPt }"
              />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="Label em Espanhol">
              <el-input
                id="labelEs"
                v-model="instance.labelEs"
                :class="{ 'has-error' : errors.labelEs }"
              />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="Label em Inglês">
              <el-input
                id="labelEn"
                v-model="instance.labelEn"
                :class="{ 'has-error' : errors.labelEn }"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="10">

          <el-col :span="8">
            <el-form-item label="Nome do Banco de Dados">
              <el-input
                id="databaseName"
                v-model="instance.databaseName"
                :class="{ 'has-error' : errors.databaseName }"
              />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="Usuário do Banco de Dados">
              <el-input
                id="databaseUser"
                v-model="instance.databaseUser"
                :class="{ 'has-error' : errors.databaseUser }"
              />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="Senha do Banco de Dados">
              <el-input
                id="databasePass"
                v-model="instance.databasePass"
                :class="{ 'has-error' : errors.databasePass }"
              />
            </el-form-item>
          </el-col>

        </el-row>

        <el-row :gutter="10">

          <el-col :span="8">
            <el-form-item label="Host do Banco de Dados">
              <el-input
                id="databaseHost"
                v-model="instance.databaseHost"
                :class="{ 'has-error' : errors.databaseHost }"
              />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="Porta do Banco de Dados">
              <el-input
                id="databasePort"
                v-model="instance.databasePort"
                :class="{ 'has-error' : errors.databasePort }"
              />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="Extra do Banco de Dados">
              <el-input
                id="databaseExtra"
                v-model="instance.databaseExtra"
                :class="{ 'has-error' : errors.databaseExtra }"
              />
            </el-form-item>
          </el-col>

        </el-row>

      </el-form>
    </div>

    <el-row :gutter="10" slot="footer">
      <el-col :span="24">
        <FooterCreateEdit
          :sendEnabled="isValidToSubmit"
          :detailsShow="instance.id ? true : false"
          @do-send="doSave()"
          @back-to-search="backToSearch()"
          @back-to-show="backToShow()" />
      </el-col>
    </el-row>
  </modulePanel>
</template>
