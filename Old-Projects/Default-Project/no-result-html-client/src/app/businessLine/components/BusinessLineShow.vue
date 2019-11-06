<script>

  import {mapActions} from 'vuex';

  import FooterShow from 'components/utils/footerCrud/FooterShow.vue';
  import appConfig from '../../../services/AppConfigService';

  export default{
      components: {
          FooterShow
      },
      data(){
          return{
            instance: {}
          }
      },
      mounted () {

        let self = this

        self.showLoader();

        self.businessLineGetInstance(self.$route.params.id)
          .then(data => {
            self.hideLoader();

            self.instance = data
          })
      },
      methods: {
          ...mapActions(['businessLineGetInstance', 'showLoader', 'hideLoader']),
          goToEdit: function () {
            let self = this;
            self.$router.push('/business-line/edit/' + self.instance.id);
          },
          goToBack: function () {
              let self = this;
              self.$router.push('/business-line');
          },
          background: function () {
              let self = this;
              self.$router.push('/business-line/background/' + self.instance.id);
          },
          resolveBackground: function() {
            if (this.instance.hasBackground) {
              // TODO foi adicionado um random value para evitar o problema de cache
              return appConfig.apiUrl() + "/api/image/businessLine/background/" + this.instance.id + "/" + Math.random();
            }
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
                <div style="height: 320px; border: 1px solid #f1f1f1">
                  <img v-if="resolveBackground()" :src="resolveBackground()" style="width: 100%;" />
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
                <label>Code</label>
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
                <label>Label em Inglês</label>
                <p>
                  {{instance.labelEn}}
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

          </el-row>

        </el-col>
      </el-row>
    </div>

    <el-row :gutter="10" slot="footer">
      <el-col :span="24">
        <FooterShow
          @edit="goToEdit()"
          @back-to-search="goToBack()"  class="pull-right" />

      </el-col>
    </el-row>
  </modulePanel>
</template>
