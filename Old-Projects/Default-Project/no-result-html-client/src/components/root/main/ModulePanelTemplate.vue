<template>
  <el-row :gutter="10">
    <el-col :span="24">
      <div class="panel">
        <div class="panel-heading" v-show="header">
          <slot name="header"></slot>
        </div>
        <div class="panel-body">
          <slot name="body"></slot>
        </div>
        <div class="panel-footer" v-show="footer">
          <slot name="footer"></slot>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
  export default {
    data() {
      return {
        header: true,
        footer: true
      }
    },
    mounted () {
      this.header = this.$slots['header'] ? true : false;
      this.footer = this.$slots['footer'] ? true : false;
    }
  }
</script>
