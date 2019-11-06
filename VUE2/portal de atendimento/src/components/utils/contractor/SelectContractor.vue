
<script>
  import {mapActions, mapGetters} from 'vuex';

  export default {
    props: ["error", "contractorId"],
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
        let self = this;
        let i;

        self.selectContractorSearch().then(() => {

          for (i = 0; i < self.list.length; i++) {

            self.options.push(self.list[i])
          }
        })
      },
      selected: function () {
          this.$emit('selected', this.selectedValue)
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
          v-model="selectedValue">
    <el-option
      v-for="item in options"
      :label="item.label"
      :value="item.id" />
    </el-select>
</template>
