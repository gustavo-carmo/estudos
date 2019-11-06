<script>

  import {mapActions} from 'vuex';

  import FooterShow from 'components/utils/footerCrud/FooterShow.vue';

  export default{
      components: {
          FooterShow
      },
      data(){
          return{
            instance: {
              contract: ''
            }
          }
      },
      mounted () {

        let self = this

        self.showLoader();

        self.formGetInstance(self.$route.params.id)
          .then(data => {
            self.hideLoader();

            self.instance = data;
          })
      },
      methods: {
          ...mapActions(['formGetInstance', 'showLoader', 'hideLoader']),
          goToEdit: function () {
            let self = this;
            self.$router.push('/form/edit/' + self.instance.id);
          },
          goToBack: function () {
              let self = this;
              self.$router.push('/form');
          },
          formBuilder: function() {
              let self = this;
              self.$router.push('/formBuilder/' + self.instance.id);
          }
      }
  }
</script>

<template>
  <modulePanel>
    <div slot="body">
      <el-row :gutter="10">
        <el-col :span="24">

          <el-row :gutter="10">
            <el-col :span="8">
              <div>
                <label>Nome</label>
                <p>
                  {{instance.name}}
                </p>
              </div>
            </el-col>

            <el-col :span="8">
              <div>
                <label>Nome da Tabela</label>
                <p>
                  {{instance.tableName}}
                </p>
              </div>
            </el-col>

            <el-col :span="8">
              <div>
                <label>Contrato</label>
                <p>
                  {{instance.contract.label}}
                </p>
              </div>
            </el-col>
          </el-row>

          <el-row :gutter="10">

            <el-col :span="8">
              <div>
                <label>Label em Português</label>
                <p>
                  {{instance.labelPt}}
                </p>
              </div>
            </el-col>

            <el-col :span="8">
              <div>
                <label>Label em Espanhol</label>
                <p>
                  {{instance.labelEs}}
                </p>
              </div>
            </el-col>

            <el-col :span="8">
              <div>
                <label>Label em Inglês</label>
                <p>
                  {{instance.labelEn}}
                </p>
              </div>
            </el-col>
          </el-row>

        </el-col>
      </el-row>
    </div>

    <el-row :gutter="10" slot="footer">
      <el-col :span="24">
        <FooterShow
          @edit="goToEdit()"
          @back-to-search="goToBack()" />
        <el-button  type="primary" size="small" @click.prevent="formBuilder()" class="pull-right">
          <i class="glyphicon glyphicon-wrench" aria-hidden="true" />
            Form builder
        </el-button>
      </el-col>
    </el-row>
  </modulePanel>
</template>
