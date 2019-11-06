<template>
  <div>
    <header class="page-header row">
      <h2>Auth!!!</h2>
    </header>

    <Login />

    <br>

    <calendar
      :value="value"
      :format="format"
      :clear-button="true"
      :pane="1"
      :has-input="true"
    ></calendar>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import Login from './Forms/Login'
  import Calendar from 'components/Calendar.vue'

  export default {
    name: 'auth',
    components: { Login, Calendar },
    computed: {
      ...mapGetters(['isLogged'])
    },
    data () {
      return {
        value: '',
        format: 'dd/MM/yyyy'
      }
    }
  }
</script>
