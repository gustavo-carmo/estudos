<script>
    import {mapActions, mapGetters} from 'vuex';

    export default {
        components: {
        },
        data () {
            return {
              filter: {},
              checked: true
            }
        },
        computed: {
          ...mapGetters({
            list: 'systemsList'
          }),
        },
        methods: {
          ...mapActions([
            'systemsDoSearch'
          ])
        },
        mounted () {
          this.systemsDoSearch('402882745af64dab015af64e419b0000');
        }
    }
</script>

<template>
  <div>

    <modulePanel>
      <div slot="body">

        <table class="table table-hover">
          <tbody>
            <tr v-for="row in list">
              <td>
                <span>{{row.labelPt}} - {{row.id}}</span>
              </td>
            </tr>
          </tbody>
        </table>

      </div>
    </modulePanel>
  </div>
</template>
