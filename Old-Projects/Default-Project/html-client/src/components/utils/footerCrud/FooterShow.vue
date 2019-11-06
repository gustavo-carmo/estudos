<script>
    export default {
    data () {
      return {
      }
    },
    methods: {
      edit: function () {
        let self = this;
        self.$emit('edit');
      },
      back: function () {
        let self = this;
        self.$emit('back-to-search');
      }
    }
  }

</script>

<template>
  <el-button-group>
    <el-button type="primary" size="small" @click.prevent="edit()" id="edit">
      <i class="glyphicon glyphicon-edit" aria-hidden="true" />
      Editar
    </el-button>

    <el-button  type="primary" size="small" @click.prevent="back()" id="backToSearch">
      <i class="glyphicon glyphicon-chevron-left" aria-hidden="true" />
      Voltar para pesquisa
    </el-button>
  </el-button-group>
</template>
