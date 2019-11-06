<script>
  import {mapActions, mapGetters} from 'vuex';

  export default {
    props: ["error", "tenantId", "placeholder"],
    data () {
      return {
        selectedValue: '',
        nameParam: ''
      }
    },
    computed: {
      ...mapGetters({
        list: 'selectTenantList',
      })
    },
    mounted () {
      this.search();
    },
    methods: {
      ...mapActions([
        'selectTenantSearch'
      ]),
      search: function () {
        this.selectTenantSearch()
      },
      selected: function () {
          this.$emit('selected', this.selectedValue);
      },
      remoteMethod(query) {
        let self = this;
        self.nameParam = query;
        self.selectTenantSearch(self.nameParam);
      }
    },
    watch: {
      tenantId: function (val) {
        let self = this;

        self.selectedValue = val
      }
    }
  }
</script>

<template>
  <el-select id="tenant"
             :class="{ 'has-error' : error }"
             @change="selected()"
             v-model="selectedValue"
             :placeholder="placeholder ? placeholder : 'Selecione'"
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
