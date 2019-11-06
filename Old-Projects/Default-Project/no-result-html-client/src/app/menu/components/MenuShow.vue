<script>

  import {mapActions} from 'vuex';

  import FooterShow from 'components/utils/footerCrud/FooterShow.vue';
  import SpanNoLinkOrForm from './SpanNoLinkOrForm';

  export default{
      components: {
          FooterShow,
          SpanNoLinkOrForm
      },
      data(){
          return{
            instance: {
              businessLine: '',
              type: '',
              contract: '',
              form: '',
              parent: ''
            }
          }
      },
      mounted () {

        let self = this

        self.showLoader();

        self.menuGetInstance(self.$route.params.id)
          .then(data => {
            self.hideLoader();

            self.instance = data;
          })
      },
      methods: {
          ...mapActions(['menuGetInstance', 'showLoader', 'hideLoader']),
          goToEdit: function () {
            let self = this;
            self.$router.push('/menu/edit/' + self.instance.id);
          },
          goToBack: function () {
              let self = this;
              self.$router.push('/menu');
          },
          menuBuilder: function() {
              let self = this;
              self.$router.push('/menuBuilder/' + self.instance.id);
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
                <label>Posição</label>
                <p>
                  {{instance.position}}
                </p>
              </div>
            </el-col>

            <el-col :span="8">
              <div>
                <label>Tipo</label>
                <p>
                  <SpanNoLinkOrForm :value="instance.type.name"/>
                </p>
              </div>
            </el-col>


          </el-row>


          <el-row :gutter="10">

            <el-col :span="8">
              <div>
                <label>Contract</label>
                <p>
                  {{instance.contract.label}}
                </p>
              </div>
            </el-col>

            <el-col :span="8">
              <div>
                <label>Menu Pai</label>
                <p>
                  {{instance.parent.label}}
                </p>
              </div>
            </el-col>

            <el-col :span="8">
              <div>
                <label>Form</label>
                <p>
                  {{instance.form.label}}
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
      </el-col>
    </el-row>
  </modulePanel>
</template>
