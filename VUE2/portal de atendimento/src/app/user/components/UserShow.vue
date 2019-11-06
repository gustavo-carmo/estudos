<script>

  import {mapActions} from 'vuex';
  import SpanStatus from 'src/components/utils/statusEnabledDisabled/SpanStatus.vue';
  import FormatDate from 'src/components/utils/FormatDate.vue';

  export default {
      components: {
        SpanStatus,
        FormatDate
      },
      data () {
          return {
              instance: {
                tenant: ''
              }
          }
      },
      mounted () {

        let self = this
          let id = self.$route.params.id
          self.userGetInstance(id)
            .then(data => {
              self.instance = data
            })
      },
      methods: {
          ...mapActions(['userGetInstance']),
          resolveLoginField: function (value) {
            if (value === 'USERNAME') {
              return 'Nome de usuario'
            }
            if (value === 'DOCUMENT_NUMBER') {
              return 'Numero de documento'
            }
          },
          back: function () {
              let self = this;
              self.$router.push('/user');
          },
           edit: function () {
              let self = this;

              self.$router.push('/user/edit/' + self.instance.id);
          },
      }
  }
</script>

<template>
  <div>
    <modulePanel>
      <h3 slot="header">Detalhes</h3>

      <div slot="body">

        <el-row :gutter="10">

          <el-col :span="8">
            <label>Nome</label>
            <p>{{instance.name}}</p>
          </el-col>

          <el-col :span="8">
            <label>Nome de Usuário</label>
            <p>{{instance.username}}</p>
          </el-col>

          <el-col :span="8">
            <label>Status</label>
            <p>
              <SpanStatus :status="instance.status"/>
            </p>
          </el-col>
        </el-row>

        <el-row :gutter="10">
          <el-col :span="12">
            <label>Email</label>
            <p>{{instance.email}}</p>
          </el-col>

          <el-col :span="12">
            <label>Tenant</label>
            <p>{{instance.tenant.label}}</p>
          </el-col>
        </el-row>

        <el-row :gutter="10">
          <el-col :span="12">
            <label>Ultimo Login</label>
            <p>
              <FormatDate :date="instance.lastLogin"/>
            </p>
          </el-col>

          <el-col :span="12">
            <label>Data de Criação</label>
            <p>
              <FormatDate :date="instance.createdDate"/>
            </p>
          </el-col>
        </el-row>

        <el-row :gutter="10">
          <el-col :span="24">
            <el-button
              type="primary"
              size="small"
              @click.prevent="edit()"
              id="edit">
              <i class="glyphicon glyphicon-edit" aria-hidden="true" />
              EDITAR
            </el-button>
            <el-button
              type="primary"
              size="small"
              @click.prevent="back()"
              id="backToSearch">
              <i class="glyphicon glyphicon-chevron-left" aria-hidden="true" />
              VOLTAR
            </el-button>
          </el-col>
        </el-row>
      </div>
    </modulePanel>
  </div>
</template>
