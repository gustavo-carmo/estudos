<template>
  <div id="app">
    <div class="block">
      <span class="demonstration">Default</span>
        <el-date-picker
          v-model="value1"
          type="date"
          placeholder="Pick a day"
          format="dd/MM/yyyy">
        </el-date-picker>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        value1: ''
      };
    }
  };
</script>