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
  <div class="container">
    <div style="margin: 30px; background-color: rgba(4,6,9,0.1); padding:30px;">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-card :body-style="{ padding: '0px' }">
            <img src="./oakley.png" class="image">
            <el-row class="boder-top-1px">
              <div class="label">
                <span>oakley</span>
              </div>
            </el-row>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card :body-style="{ padding: '0px' }">
            <img src="./carrefour.png" class="image">
            <el-row class="boder-top-1px">
              <div class="label">
                <span>Carrefour</span>
              </div>
            </el-row>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card :body-style="{ padding: '0px' }">
            <img src="./renner.png" class="image">
            <el-row class="boder-top-1px">
              <div class="label">
                <span>Renner</span>
              </div>
            </el-row>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card :body-style="{ padding: '0px' }">
            <img src="./riachuelo.png" class="image">
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
            <img src="./youcom.png" class="image">
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

