<script>

  import {mapActions} from 'vuex';

  import FooterShow from 'components/utils/footerCrud/FooterShow.vue';
  import appConfig from '../../../services/AppConfigService';
  import contractUtils from '../ContractUtils.js';

  export default{
      components: {
          FooterShow
      },
      data(){
          return{
            instance: {
              businessLine: ''
            }
          }
      },
      mounted () {

        let self = this

        self.showLoader();

        self.contractGetInstance(self.$route.params.id)
          .then(data => {
            self.hideLoader();

            self.instance = data;
          })
      },
      methods: {
          ...mapActions(['contractGetInstance', 'showLoader', 'hideLoader']),
          goToEdit: function () {
            let self = this;
            self.$router.push('/contract/edit/' + self.instance.id);
          },
          goToBack: function () {
              let self = this;
              self.$router.push('/contract');
          },
          background: function () {
              let self = this;
              self.$router.push('/contract/image/background/' + self.instance.id);
          },
          resolveBackground: function() {
            // TODO foi adicionado um random value para evitar o problema de cache
            if (this.instance.hasBackground) {
              return appConfig.apiUrl() + "/api/image/contract/background/" + this.instance.id + "/" + Math.random();
            }
          },
          logo: function () {
              let self = this;
              self.$router.push('/contract/image/logo/' + self.instance.id);
          },
          resolveLogo: function() {
            // TODO foi adicionado um random value para evitar o problema de cache
            if (this.instance.hasLogo) {
              return appConfig.apiUrl() + "/api/image/contract/logo/" + this.instance.id + "/" + Math.random();
            }
          },
          deploy: function() {
            // alert('Sendo implementado...');

            var self = this;

            self.showLoader();

            contractUtils.deploy(this.instance.id)
              .then(function(response) {

                self.hideLoader();

                self.$notify({
                  title: 'Sucesso',
                  message: 'Deploy realizaso',
                  type: 'success'
                });

              });
            // pegar id do contrato
            // enviar para rota que faz deploy
            // sucesso: exibir aba de deploy
            // falha: exibir mensagem de erro

            //
          }
      }
  }
</script>

<template>
  <modulePanel>
    <div slot="body">
      <el-row :gutter="10">
        <el-col :span="10">

          <el-row :gutter="10">
            <el-col :span="24" style="padding-bottom: 20px">
              <div style="height: 80px; width: 80px; border: 1px solid #f1f1f1">
                <img v-if="resolveLogo()" :src="resolveLogo()" style="width: 100%; height: 100%" />
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="24">
              <el-button size="small" @click.prevent="logo()">
                <i class="glyphicon glyphicon-picture" aria-hidden="true" />
                Selecionar logo
              </el-button>
            </el-col>
          </el-row>

          <el-row :gutter="10">
            <el-col :span="24" style="padding: 20px 0px">
              <hr />
            </el-col>
          </el-row>

          <el-row :gutter="10">
            <el-col :span="24" style="padding-bottom: 20px">
              <div style="height: 320px; width: 320px; border: 1px solid #f1f1f1">
                <img v-if="resolveBackground()" :src="resolveBackground()" style="width: 100%; height: 100%" />
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="24">
              <el-button size="small" @click.prevent="background()">
                <i class="glyphicon glyphicon-picture" aria-hidden="true" />
                Selecionar background
              </el-button>
            </el-col>
          </el-row>

        </el-col>
        <el-col :span="14">

          <el-row :gutter="10">
            <el-col :span="8">
              <div>
                <label>Código</label>
                <p>
                  {{instance.code}}
                </p>
              </div>
            </el-col>

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
                <label>Business Line</label>
                <p>
                  {{instance.businessLine.label}}
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

          <el-row :gutter="10">

            <el-col :span="8">
              <div>
                <label>Nome do Banco de Dados</label>
                <p>
                  {{instance.databaseName}}
                </p>
              </div>
            </el-col>

            <el-col :span="8">
              <div>
                <label>Usuário do Banco de Dados</label>
                <p>
                  {{instance.databaseUser}}
                </p>
              </div>
            </el-col>

            <el-col :span="8">
              <div>
                <label>Senha do Banco de Dados</label>
                <p>
                  {{instance.databasePass}}
                </p>
              </div>
            </el-col>

          </el-row>

          <el-row :gutter="10">

            <el-col :span="8">
              <div>
                <label>Host do Banco de Dados</label>
                <p>
                  {{instance.databaseHost}}
                </p>
              </div>
            </el-col>

            <el-col :span="8">
              <div>
                <label>Porta do Banco de Dados</label>
                <p>
                  {{instance.databasePort}}
                </p>
              </div>
            </el-col>

            <el-col :span="8">
              <div>
                <label>Extra do Banco de Dados</label>
                <p>
                  {{instance.databaseExtra}}
                </p>
              </div>
            </el-col>

          </el-row>
        </el-col>
      </el-row>
    </div>

    <el-row :gutter="10" slot="footer">
      <el-col :span="24">

        <el-button size="small" type="success" @click.prevent="deploy()">
          <i class="glyphicon glyphicon-plane" aria-hidden="true" />
          Executar deploy
        </el-button>
        <!--<el-button size="small" type="success" @click.prevent="deploy()">
          <i class="glyphicon glyphicon-plane" aria-hidden="true" />
          Deploy realizados
        </el-button>-->

        <FooterShow
          @edit="goToEdit()"
          @back-to-search="goToBack()" class="pull-right"
        />
          <div>
            <!--<el-button size="small" @click.prevent="background()">
              <i class="glyphicon glyphicon-picture" aria-hidden="true" />
                Selecionar background
            </el-button>-->
            <!--<el-button size="small" @click.prevent="logo()">
              <i class="glyphicon glyphicon-picture" aria-hidden="true" />
                Selecionar logo
            </el-button>-->
          </div>
      </el-col>

    </el-row>
  </modulePanel>
</template>
