<script>

  import {mapActions} from 'vuex';

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
            code: '',
            background: ''
          },
          errors: [],
          fieldsWithErrors: []
        }
      },
      computed: {
        messageCreateOrUpdate: function () {
          return this.instance.id ? 'atualizada' : 'criada'
        },
        isValidToSubmit: function () {
            let self = this;

            return self.instance.name &&
                self.instance.labelPt &&
                self.instance.code
        },
      },
      mounted () {

        let self = this
        let id = self.$route.params.id

        if (id) {
          self.businessLineGetInstance(id)
            .then(data => {

              self.instance.id = data.id;
              self.instance.name = data.name;
              self.instance.labelPt = data.labelPt;
              self.instance.labelEs = data.labelEs;
              self.instance.labelEn = data.labelEn;
              self.instance.code = data.code;
              self.instance.background = data.background;
            })
        }
      },
      methods: {
          ...mapActions([
            'businessLineGetInstance',
            'businessLineSave',
            'showLoader',
            'hideLoader'
            ]),

          doSave: function () {
            let self = this;

            self.showLoader();
            self.businessLineSave(self.instance)
              .then(response => {

                self.hideLoader();

                self.$notify({
                  title: 'Sucesso',
                  message: 'Linha de Negócio ' + self.messageCreateOrUpdate + ' com sucesso.',
                  type: 'success'
                });

                self.$router.push('/business-line/show/' + response.id);

                self.instance = {}
              },
              response => {

                self.hideLoader();

                self.errors = response;
              });
          },
          backToSearch: function () {
              let self = this;
              self.$router.push('/business-line');
          },
          backToShow: function () {
              let self = this;

              self.$router.push('/business-line/show/' + self.$route.params.id);
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
            <el-form-item label="Label em Inglês">
              <el-input
                id="labelEn"
                v-model="instance.labelEn"
                :class="{ 'has-error' : errors.labelEn }"
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
