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
        list: 'selectServiceOfferedList',
      })
    },
    mounted () {
      this.search();
    },
    methods: {
      ...mapActions([
        'selectServiceOfferedSearch'
      ]),
      search: function () {
        this.selectServiceOfferedSearch()
      },
      selected: function () {
          this.$emit('selected', this.selectedValue)
      },
      remoteMethod(query) {
        let self = this;
        self.nameParam = query;
        self.selectServiceOfferedSearch(self.nameParam);
      }
    }
  }

</script>

<template>
  <el-select id="serviceOffered"
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
      :value="item.id"
      :label="item.label">
    </el-option>
  </el-select>
</template>
