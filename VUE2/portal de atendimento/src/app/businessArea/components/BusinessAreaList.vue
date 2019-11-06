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
            list: 'businessAreaList'
          }),
        },
        methods: {
          ...mapActions([
            'businessAreaDoSearch'
          ])
        },
        mounted () {
          this.businessAreaDoSearch();
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
                <span>{{row.name}} - {{row.id}}</span>
              </td>
            </tr>
          </tbody>
        </table>

      </div>
    </modulePanel>
  </div>
</template>
