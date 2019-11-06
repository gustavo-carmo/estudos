<script>
  import {mapActions, mapGetters} from 'vuex';

  export default {
    props: ["error"],
    data () {
      return {
        nameParam: '',
        selectedValue: ''
      }
    },
    computed: {
      ...mapGetters({
        list: 'selectDefectList',
      })
    },
    mounted () {
      this.search();
    },
    methods: {
      ...mapActions([
        'selectDefectSearch'
      ]),
      search: function () {
        this.selectDefectSearch()
      },
      selected: function () {
          this.$emit('selected', this.selectedValue)
      },
      remoteMethod(query) {
        let self = this;
        self.nameParam = query;
        self.selectDefectSearch(self.nameParam);
      }
    }
  }

</script>


<template>
  <el-select id="defect"
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
      :value="item.id">
    </el-option>
    </el-select>
</template>
