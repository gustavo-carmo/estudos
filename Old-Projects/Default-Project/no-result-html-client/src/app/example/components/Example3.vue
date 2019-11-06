<script>
  import ModuleTemplate from 'src/components/root/main/ModuleTemplate.vue'
  export default {
      data() {
          return {
          }
      },
      components: {
        ModuleTemplate
      }
  }
</script>
<template>
  <module-template>
    <modulePanel>
      <div slot="body">
        <div style="margin: 30px">
          <el-row :gutter="20">
            <el-col :span="6">
              <el-card :body-style="{ padding: '0px' }">
                <img src="https://psydownload.files.wordpress.com/2011/01/plano-de-fundo-zc3adper.jpg" class="image">
                <el-row class="boder-top-1px">
                  <div class="label">
                    <span>oakley</span>
                  </div>
                </el-row>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card :body-style="{ padding: '0px' }">
                <img src="https://s-media-cache-ak0.pinimg.com/736x/08/4a/0f/084a0f321b822d481c29181f97d188fc.jpg" class="image">
                <el-row class="boder-top-1px">
                  <div class="label">
                    <span>Carrefour</span>
                  </div>
                </el-row>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card :body-style="{ padding: '0px' }">
                <img src="https://fjinfor.files.wordpress.com/2012/08/3d-wallpaper.jpg" class="image">
                <el-row class="boder-top-1px">
                  <div class="label">
                    <span>Renner</span>
                  </div>
                </el-row>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card :body-style="{ padding: '0px' }">
                <img src="https://image.freepik.com/free-vector/blue-digital-technology-background_1017-1927.jpg" class="image">
                <el-row class="boder-top-1px">
                  <div class="label">
                    <span>Riachuelo</span>
                  </div>
                </el-row>
              </el-card>
            </el-col>
          </el-row>

          <el-row :gutter="20" style="padding-top: 20px;">
            <el-col :span="6">
              <el-card :body-style="{ padding: '0px' }">
                <img src="https://image.freepik.com/vetores-gratis/fundo-a-tecnologia-digital-composto-por-linhas-de-malha_1017-7135.jpg" class="image">
                <el-row class="boder-top-1px">
                  <div class="label">
                    <span>Youcom</span>
                  </div>
                </el-row>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </div>

    </modulePanel>
  </module-template>
</template>
<style>
.margin-top-10{
  margin-top: 10%;
}
.image {
  display: block;
  height: 200px;
  width: 100%;
  margin:0;
}
.label{
  padding: 14px;
  color: black
}
boder-top-1px{
  border-top: solid 1px black;
}
/*.panel {
    background-image: url('http://www.qygjxz.com/data/out/18/5769022-background-image.png');
    background-repeat: no-repeat;
    background-size:100%;
    bottom: 0;
    color: black;
    left: 0;
    right: 0;
    top: 0;
    height: 100%;
    width: 100%;
}
.panel-heading {
    border-bottom: none;
}*/










</style>

