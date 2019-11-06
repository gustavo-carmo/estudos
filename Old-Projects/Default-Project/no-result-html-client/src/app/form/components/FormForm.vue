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
            name: '',
            labelPt: '',
            labelEs: '',
            labelEn: '',
            contract: { id: '' },
            tableName: ''
          },
          errors: [],
          fieldsWithErrors: [],
        }
      },
      computed: {
        ...mapGetters({
          contractList: 'contractList'
        }),
        messageCreateOrUpdate: function () {
          return this.instance.id ? 'atualizado' : 'criado'
        },
        isValidToSubmit: function () {
            let self = this;

            return self.instance.contract &&
                self.instance.name &&
                self.instance.tableName &&
                self.instance.labelPt
        },
      },
      mounted () {

        let self = this
        let id = self.$route.params.id

        self.contractSearch();

        if (id) {
          self.formGetInstance(id)
            .then(data => {

              self.instance = data;
            });
        }
      },
      methods: {
          ...mapActions([
            'formGetInstance',
            'formSave',
            'showLoader',
            'hideLoader',
            'contractSearch'
            ]),

          doSave: function () {
            let self = this;

            self.showLoader();
            self.formSave(self.instance)
              .then(response => {

                self.hideLoader();

                self.$notify({
                  title: 'Sucesso',
                  message: 'Form ' + self.messageCreateOrUpdate + ' com sucesso.',
                  type: 'success'
                });

                self.$router.push('/form/show/' + response.id);

                self.instance = {}
              },
              response => {

                self.hideLoader();

                self.errors = response;
              });
          },
          backToSearch: function () {
              let self = this;
              self.$router.push('/form');
          },
          backToShow: function () {
              let self = this;

              self.$router.push('/form/show/' + self.$route.params.id);
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
            <el-form-item label="Nome">
              <el-input
                id="name"
                v-model="instance.name"
                :class="{ 'has-error' : errors.name }"
              />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="Nome da Tabela">
              <el-input
                id="tableName"
                v-model="instance.tableName"
                :class="{ 'has-error' : errors.tableName }"
              />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="Contrato">

              <el-select
                :placeholder="'Selecione'"
                :class="{ 'has-error' : errors.contract }"
                v-model="instance.contract.id">
                <el-option
                  v-for="item in contractList"
                  :label="item.nameExtended"
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
