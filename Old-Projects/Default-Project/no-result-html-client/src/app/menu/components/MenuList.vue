<script>
    import {mapActions, mapGetters} from 'vuex';

    import SpanNoLinkOrForm from './SpanNoLinkOrForm';

    export default{
        data(){
            return{
            }
        },
        components: {
          SpanNoLinkOrForm
        },
        computed: {
          ...mapGetters({
            list: 'menuList',
            stateFilter: 'menuFilter',
            pagination: 'menuPagination'
          })
        },
        methods: {
          ...mapActions([
            'menuDoSearch'
          ]),
          resolveStatus: function () {

            let labels = {
              'NO_LINK': 'Sem Link',
              'FORM': 'Formulario'
            }

            return labels[this.value]
          },
          goToCreate: function () {
              this.$router.push('/menu/create');
          },
          goToShow: function (menu) {
              this.$router.push('/menu/show/' + menu.id);
          },
          goToEdit: function (menu) {
              this.$router.push('/menu/edit/' + menu.id);
          }
        },
        mounted () {
          this.menuDoSearch();
        }
    }

</script>

<template>
  <div>
    <modulePanel>
      <div slot="body">
        <el-form>
          <el-row>
            <el-button size="small" type="primary" @click.prevent="goToCreate()">
              <i class="glyphicon glyphicon-plus" aria-hidden="true"/>
              NOVO
            </el-button>
          </el-row>
        </el-form>
      </div>
    </modulePanel>

    <modulePanel>
      <div slot="body">

        <div class="hidden-xs">
          <el-table
            :data="list"
          >

            <el-table-column
              width="100"
              inline-template>

              <div>
                <el-button
                  size="mini"
                  icon="document"
                  @click.prevent="goToShow(row)">
                </el-button>

                <el-button
                  size="mini"
                  icon="edit"
                  @click.prevent="goToEdit(row)">
                </el-button>
              </div>

            </el-table-column>

            <el-table-column
              inline-template
              label="Nome">
              <div>{{row.name}}</div>
            </el-table-column>

            <el-table-column
              inline-template
              label="Contract">
              <div>{{row.contract.label}}</div>
            </el-table-column>

            <el-table-column
              inline-template
              label="Tipo de Menu">
              <div>
                <SpanNoLinkOrForm :value="row.type.name"/>
              </div>
            </el-table-column>

          </el-table>
        </div>

        <!--<el-row :gutter="10" class="margin-top-15" v-show="pagination.totalPage > 1">
          <el-col :md="24" :xs="24">
            <Pagination :pagination="pagination" @change-page="serviceOrderSetPage"
                        @change-items-per-page="serviceOrderSetItemsPerPage"/>
          </el-col>
        </el-row>-->
      </div>
    </modulePanel>
  </div>
</template>
