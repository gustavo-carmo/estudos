<script>
  import {mapActions, mapGetters} from 'vuex';

  export default {
    props: ["error"],
    data () {
      return {
        selectedValue: ''
      }
    },
    computed: {
      ...mapGetters({
        list: 'selectModelList',
      })
    },
    mounted () {
      this.search();
    },
    methods: {
      ...mapActions([
        'selectModelSearch'
      ]),
      search: function () {
        this.selectModelSearch()
      },
      selected: function () {
          this.$emit('selected', this.selectedValue)
      },
      remoteMethod(query) {
        let self = this;
        self.nameParam = query;
        self.selectModelSearch(self.nameParam);
      }
    }
  }

</script>

<template>
  <el-select id="model"
         :class="{ 'has-error' : error }"
         @change="selected()"
         v-model="selectedValue"
         placeholder=""
         filterable
         clearable
         :remote-method="remoteMethod"
         :loading="loading" >
    <el-option
      v-for="item in list"
      :label="item.label"
      :value="item.id" />
    </el-select>
</template>
