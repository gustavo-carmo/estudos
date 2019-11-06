
<script>
  import {mapActions, mapGetters} from 'vuex';

  export default {
    props: ["error", "tenantId", "placeholder"],
    data () {
      return {
        selectedValue: '',
        options: [
          {
            value: '',
            label: ''
          }
         ]
      }
    },
    mounted () {
      this.search();
    },
    methods: {
      ...mapActions([
        'selectTenantDoSearch'
      ]),
      search: function () {
        let self = this;
        let i;

        self.selectTenantDoSearch().then(() => {

          for (i = 0; i < self.list.length; i++) {

            self.options.push(self.list[i])
          }
        })
      },
      selected: function () {
          this.$emit('selected', this.selectedValue)
      }
    },
    computed: {
      ...mapGetters({
        list: 'selectTenantList',
      })
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
          :placeholder="placeholder ? placeholder : 'Selecione'"
          v-model="selectedValue">
    <el-option
      v-for="item in options"
      :label="item.label"
      :value="item.id" />
    </el-select>
</template>
