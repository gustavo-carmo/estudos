<script>
    export default {
    props: ["sendEnabled", "detailsShow"],
    data () {
      return {

      }
    },
    methods: {
      doSend: function () {
        let self = this;
        self.$emit('do-send');
      },
      backToSearch: function () {
        let self = this;
        self.$emit('back-to-search');
      },
      backToShow: function () {
        let self = this;
        self.$emit('back-to-show');
      }
    },
    mounted () {

      let self = this;

      self.sendEnabled = self.sendEnabled == null ? true : self.sendEnabled;
      self.detailsShow = self.detailsShow == null ? true : self.detailsShow;
    }
  }
</script>

<template>
  <el-button-group>
    <el-button type="primary" size="small" @click.prevent="doSend()" :disabled="!sendEnabled">
      <i class="glyphicon glyphicon-ok" />
      Enviar
    </el-button>

    <el-button type="default" size="small" @click.prevent="backToSearch()">
      <i class="glyphicon glyphicon-chevron-left" />
      Voltar para pesquisa
    </el-button>

    <el-button type="default" size="small" @click.prevent="backToShow()" v-if="detailsShow">
      <i class="glyphicon glyphicon-chevron-left" />
      Voltar para detalhes
    </el-button>
  </el-button-group>
</template>
