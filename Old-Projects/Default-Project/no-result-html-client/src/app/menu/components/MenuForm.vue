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
            parent: { id: '' },
            form: { id: '' },
            type: '',
            position: ''

          },
          errors: [],
          fieldsWithErrors: [],
          typeList: [
            {
              value: '',
              label: ''
            },
            {
              value: 'NO_LINK',
              label: 'Sem Link'
            },
            {
              value: 'FORM',
              label: 'Formulario'
            }
          ]
        }
      },
      computed: {
        ...mapGetters({
          contractList: 'contractList',
          formList: 'formList',
          menuList: 'menuList'
        }),
        messageCreateOrUpdate: function () {
          return this.instance.id ? 'atualizado' : 'criado'
        },
        isValidToSubmit: function () {
            let self = this;

            return self.instance.contract &&
                self.instance.name &&
                self.instance.type &&
                self.instance.labelPt &&
                self.instance.position
        },
      },
      mounted () {

        let self = this
        let id = self.$route.params.id

        self.contractSearch();
        self.formSearch();
        self.menuSearch();

        if (id) {
          self.menuGetInstance(id)
            .then(data => {

              self.instance = data;
            });
        }
      },
      methods: {
          ...mapActions([
            'menuGetInstance',
            'menuSave',
            'showLoader',
            'hideLoader',
            'contractSearch',
            'formSearch',
            'menuSearch'
            ]),

          doSave: function () {
            let self = this;

            self.showLoader();
            self.menuSave(self.instance)
              .then(response => {

                self.hideLoader();

                self.$notify({
                  title: 'Sucesso',
                  message: 'Menu ' + self.messageCreateOrUpdate + ' com sucesso.',
                  type: 'success'
                });

                self.$router.push('/menu/show/' + response.id);

                self.instance = {}
              },
              response => {

                self.hideLoader();

                self.errors = response;
              });
          },
          backToSearch: function () {
              let self = this;
              self.$router.push('/menu');
          },
          backToShow: function () {
              let self = this;

              self.$router.push('/menu/show/' + self.$route.params.id);
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
            <el-form-item label="Posição">
              <el-input-number
                id="position"
                v-model="instance.position"
                :class="{ 'has-error' : errors.position }"
              />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="Tipo">
              <el-select
                :placeholder="'Selecione'"
                :class="{ 'has-error' : errors.type }"
                v-model="instance.type">
                <el-option
                  v-for="item in typeList"
                  :label="item.label"
                  :value="item.value" />
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row :gutter="10">

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

          <el-col :span="8">
            <el-form-item label="Menu Pai">
              <el-select
                :placeholder="'Selecione'"
                :class="{ 'has-error' : errors.parent }"
                v-model="instance.parent.id">
                <el-option
                  v-for="item in menuList"
                  :label="item.nameExtended"
                  :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="Form">

              <el-select
                :placeholder="'Selecione'"
                :class="{ 'has-error' : errors.form }"
                v-model="instance.form.id">
                <el-option
                  v-for="item in formList"
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
