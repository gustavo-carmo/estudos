<template>
  <div class="hello">
    <div class="block">
      <span class="demonstration">Picker with quick options</span>
      <el-date-picker
        v-model="value6"
        type="date"
        placeholder="Pick a day"
        :picker-options="pickerOptions1">
      </el-date-picker>
    </div>
    <hr>
    <div class="block">
      <span class="demonstration">With quick options</span>
      <el-date-picker
        v-model="value7"
        type="daterange"
        align="right"
        placeholder="Pick a range"
        :picker-options="pickerOptions2"
        style="width: 220px">
      </el-date-picker>
    </div>
  </div>
</template>

<script>
export default {
  name: 'hello',
  data () {
    return {
      pickerOptions1: {
        disabledDate: function (date) {
          date.setDate(date.getDate() + 181)
          return !(date > new Date())
        }
      },
      pickerOptions2: {
        disabledDate: function (date) {
          date.setDate(date.getDate() + 181)
          return !(date > new Date())
        }
      },
      value6: '',
      value7: ''
    }
  },
  methods: {
    show: function () {
      console.log(this.value6)
    },
    dateDisabled: function () {
      console.log(new Date())
      return new Date()
    }
  }
}
</script>
