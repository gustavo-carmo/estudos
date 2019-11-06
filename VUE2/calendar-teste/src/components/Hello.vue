<template>
  <div class="hello">
    <datepicker format="YYYY-M-D" value="2015-9-5"></datepicker>
  </div>
</template>

<script>
import datepicker from 'vue-date-picker'
export default {
  name: 'hello',
  components: { datepicker },
  data () {
    return {
    }
  }
}
</script>