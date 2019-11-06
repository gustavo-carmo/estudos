<script>
  import {mapActions, mapGetters} from 'vuex';

  export default {
    props: ["error", "contractorId"],
    data () {
      return {
        selectedValue: '',
        nameParam: ''
      }
    },
    computed: {
      ...mapGetters({
        list: 'selectContractorList',
      })
    },
    mounted () {
      this.search();
    },
    methods: {
      ...mapActions([
        'selectContractorSearch'
      ]),
      search: function () {
        this.selectContractorSearch()
      },
      selected: function () {
          this.$emit('selected', this.selectedValue);
      },
      remoteMethod(query) {
        let self = this;
        self.nameParam = query;
        self.selectContractorSearch(self.nameParam);
      }
    },
    watch: {
      contractorId: function (val) {
        let self = this;

        self.selectedValue = val
      }
    }
  }
</script>

<template>
  <el-select id="contractor"
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
