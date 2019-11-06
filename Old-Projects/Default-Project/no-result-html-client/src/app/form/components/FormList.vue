<script>
    import {mapActions, mapGetters} from 'vuex';

    export default{
        data(){
            return{
            }
        },
        computed: {
          ...mapGetters({
            list: 'formList',
            stateFilter: 'formFilter',
            pagination: 'formPagination'
          })
        },
        methods: {
          ...mapActions([
            'formDoSearch'
          ]),
          goToCreate: function () {
              this.$router.push('/form/create');
          },
          goToShow: function (form) {
              this.$router.push('/form/show/' + form.id);
          },
          goToEdit: function (form) {
              this.$router.push('/form/edit/' + form.id);
          }
        },
        mounted () {
          this.formDoSearch();
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
              label="Contrato">
              <div>{{row.contract.label}}</div>
            </el-table-column>

            <el-table-column
              inline-template
              label="Nome da tabela">
              <div>{{row.tableName}}</div>
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
