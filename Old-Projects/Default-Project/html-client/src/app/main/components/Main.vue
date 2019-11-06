<template>
  <modulePanel>
    <h3 slot="header">Pagina de Exemplos</h3>

    <div slot="body">
      <el-row>
        <el-col :span="6">Text</el-col>
        <el-col :span="18">
          <SgsRender type="text" />
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="6">Numeric</el-col>
        <el-col :span="18">
          <SgsRender type="numeric" />
        </el-col>
      </el-row>
    </div>

  </modulePanel>
</template>

<script>

  import SgsRender from 'src/components/utils/sgs/SgsRender.vue';

  export default{
      data(){
          return{
          }
      },
      components: {
          SgsRender,
      }
  }
</script>
