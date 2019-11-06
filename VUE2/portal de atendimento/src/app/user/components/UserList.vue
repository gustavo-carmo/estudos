<script>

    import SpanStatus from 'src/components/utils/statusEnabledDisabled/SpanStatus.vue';
    import SelectStatus from 'src/components/utils/statusEnabledDisabled/SelectStatus.vue';
    import Pagination from 'src/components/utils/Pagination.vue';
    import FormatDate from 'src/components/utils/FormatDate.vue';
    import SelectTenant from 'src/components/utils/tenant/SelectTenant.vue';

    import {mapActions, mapGetters} from 'vuex';

    export default {
        components: {
          SpanStatus,
          SelectStatus,
          Pagination,
          FormatDate,
          SelectTenant
        },
        data () {
            return {
              filter: {}
            }
        },
        computed: {
          ...mapGetters({
            list: 'userList',
            stateFilter: 'userFilter',
            pagination: 'userPagination'
          })
        },
        mounted () {
          this.filter = this.stateFilter;
          this.userDoSearch(this.filter);
        },
        methods: {
          ...mapActions([
            'userDoSearch',
            'userSetPage',
            'userSetItemsPerPage'
          ]),
          setTenantValue: function (value) {
            this.filter.tenantId = value
          },
          setStatus: function (value) {
            this.filter.status = value;
          },
          create: function () {
              let self = this;
              self.$router.push('/user/create');
          },
           edit: function (user) {
              let self = this;
              self.$router.push('/user/edit/' + user.id);
          },
          show: function (user) {
              let self = this;
              self.$router.push('/user/show/' + user.id);
          },
        }
    }
</script>

<template>
  <div>
    <modulePanel>

      <el-form slot="body">
        <el-row>
          <el-col :md="19" :xs="24">

            <el-row :gutter="10">

              <el-col :md="6">
                <el-form-item>
                  <el-input
                    placeholder="Nome"
                    id="nome"
                    v-model="filter.name"
                  />
                </el-form-item>
              </el-col>

              <el-col :md="6">
                <el-form-item>
                  <el-input
                    placeholder="Nome de Usuário"
                    id="username"
                    v-model="filter.username"
                  />
                </el-form-item>
              </el-col>

              <el-col :md="6">
                <el-form-item>
                  <SelectStatus
                    :status="filter.status"
                    placeholder="Status"
                    @selected="setStatus"
                  />
                </el-form-item>
              </el-col>

              <el-col :md="6" class="hidden-xs">
                <el-form-item>
                  <SelectTenant
                    placeholder="Tenant"
                    @selected="setTenantValue"
                    :tenantId="filter.tenantId"
                  />
                </el-form-item>
              </el-col>

            </el-row>
          </el-col>

          <el-col :md="5" :xs="24">
            <el-form-item class="pull-right">
              <el-button size="small" type="primary" @click.prevent="userDoSearch(filter)">
                <i class="glyphicon glyphicon-search" aria-hidden="true"/>
                PESQUISAR
              </el-button>
              <el-button size="small" type="primary" @click.prevent="create()">
                <i class="glyphicon glyphicon-plus" aria-hidden="true"/>
                NOVO
              </el-button>
            </el-form-item>
          </el-col>

        </el-row>
      </el-form>

    </modulePanel>

    <modulePanel>
      <div slot="body">

        <el-row :gutter="10" class="margin-bottom-15">
          <el-col :md="24" :xs="24">
            <Pagination :pagination="pagination" @change-page="userSetPage"
                        @change-items-per-page="userSetItemsPerPage"/>
          </el-col>
        </el-row>

        <el-table
          :data="list"
        >
          <el-table-column
            width="100"
            inline-template
          >
            <div>
              <el-button
                size="mini"
                icon="document"
                @click.prevent="show(row)">
              </el-button>

              <el-button
                size="mini"
                icon="edit"
                @click.prevent="edit(row)">
              </el-button>
            </div>
          </el-table-column>

          <el-table-column
            inline-template
            label="Nome">
            <div>
              {{row.name}}
            </div>
          </el-table-column>

          <el-table-column
            inline-template
            label="Nome de Usuário">
            <div>
              {{row.username}}
            </div>
          </el-table-column>

          <el-table-column
            inline-template
            label="Tenant">
            <div>
              {{row.tenant.label}}
            </div>
          </el-table-column>

          <el-table-column
            inline-template
            label="Status">
            <div>
              <SpanStatus :status="row.status"/>
            </div>
          </el-table-column>

          <el-table-column
            inline-template
            label="Ultimo Login">
            <div>
              <FormatDate :date="row.lastLogin"/>
            </div>
          </el-table-column>
        </el-table>

        <el-row :gutter="10" class="margin-top-15">
          <el-col :md="24" :xs="24">
            <Pagination :pagination="pagination" @change-page="userSetPage"
                        @change-items-per-page="userSetItemsPerPage"/>
          </el-col>
        </el-row>

      </div>
    </modulePanel>
  </div>
</template>
