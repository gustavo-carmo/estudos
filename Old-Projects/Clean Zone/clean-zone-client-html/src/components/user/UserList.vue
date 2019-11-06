<template>
    <!-- Filtro -->
    <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-12">

            <div id="accordion" class="panel-group accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                <i class="fa fa-angle-right"></i>
                                Filtro
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">

                            <form>

                                <div class="row">

                                    <div class="col-sm-3">
                                        <div class="form-group">
                                            <label>Nome</label>
                                            <input type="text" class="form-control" v-model="filter.name">
                                        </div>
                                    </div>

                                    <div class="col-sm-3">
                                        <div class="form-group">
                                            <label>Nome de usuário</label>
                                            <input type="text" class="form-control" v-model="filter.username">
                                        </div>
                                    </div>

                                    <div class="col-sm-2">
                                        <div class="form-group">
                                            <label class="control-label">Habilitado</label>
                                            <select class="form-control" v-model="filter.enabled">
                                                <option value=""></option>
                                                <option value="true">Sim</option>
                                                <option value="false">Não</option>
                                            </select>
                                        </div>
                                    </div>

                                    <!-- TODO - ver um componente de data melhor
                                    <div class="col-sm-2">
                                        <div class="form-group">
                                            <label>Último login (Início)</label>
                                            <simple-datepicker
                                                    :value.sync="filter.lastLoginStart"
                                                    format="DD/MM/YYYY"></simple-datepicker>
                                        </div>
                                    </div>-->

                                    <!-- TODO - ver um componente de data melhor
                                    <div class="col-sm-2">
                                        <div class="form-group">
                                            <label>Último login (Fim)</label>
                                            <simple-datepicker
                                                    :value.sync="filter.lastLoginEnd"
                                                    format="DD/MM/YYYY"></simple-datepicker>
                                        </div>
                                    </div>-->

                                </div>

                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group margin-bottom-0">
                                            <button class="btn btn-primary" @click.prevent="doSearch">Pesquisar</button>
                                            <button class="btn btn-default" @click.prevent="create">Novo</button>
                                        </div>
                                    </div>

                                </div>

                            </form>

                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- Fim do filtro -->

    <!-- Tabela -->
    <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-12">
            <div class="block-flat" v-loading="isLoading">

                <pagination :pagination="pagination" @change-page="onChangePage"></pagination>

                <div class="table-responsive">
                    <table class="table no-border hover">
                        <thead class="no-border">
                        <tr>
                            <th width="1px">&nbsp;</th>
                            <th><strong>Nome</strong></th>
                            <th><strong>Nome de usuário</strong></th>
                            <th><strong>Email</strong></th>
                            <th><strong>Último login</strong></th>
                            <th><strong>Habilitado</strong></th>
                        </tr>
                        </thead>
                        <tbody class="no-border-y">
                        <tr v-for="user in users">
                            <td nowrap>
                                <button class="btn btn-default btn-xs" type="button" @click.prevent="show(user)">
                                    <i class="fa fa-file"></i>
                                </button>
                                <button class="btn btn-primary btn-xs" type="button" @click.prevent="edit(user)">
                                    <i class="fa fa-pencil"></i>
                                </button>
                            </td>
                            <td>{{ user.name }}</td>
                            <td>{{ user.username }}</td>
                            <td>{{ user.email }}</td>
                            <td><span v-if="user.lastLogin"><i class="fa fa-calendar"></i>{{ user.lastLogin | moment "DD/MM/YYYY" }}</span></td>
                            <td>
                                <span class="label" :class="{ 'label-success' : user.enabled, 'label-danger' : !user.enabled }">
                                    {{ user.enabled | formatBoolean }}
                                </span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <pagination :pagination="pagination" @change-page="onChangePage"></pagination>
            </div>
        </div>
    </div>
    <!-- Fim da tabela -->


</template>
<script>

    import UserService from './UserService.vue';
    import Pagination from '../utils/Pagination.vue';
    import loading from 'vue-loading';

    export default{
        directives: {loading},
        data(){
            return {
                filter: {},
                users: [],
                pagination: {},
                isLoading: false
            }
        },
        ready() {
            this.search();
        },
        components: {
            Pagination
        },
        methods: {
            onChangePage(page) {
                let self = this;
                self.filter.currentPage = page;
                self.search();
            },
            doSearch: function () {
                let self = this;
                self.filter.currentPage = 1;
                self.search();
            },
            search: function () {
                let self = this;
                self.isLoading = true;
                self.users = [];
                UserService.searchUser(
                        self.$http,
                        self.filter,
                        function (data) {
                            self.users.push(data);
                            self.isLoading = false;
                        },
                        function (data) {
                            self.pagination = data;
                        }
                );
            },
            create: function () {
                let self = this;
                self.$route.router.go({
                    path: '/user/create'
                });
            },
            show: function (user) {
                let self = this;
                self.$route.router.go({
                    path: '/user/show/' + user.id
                });
            },
            edit: function (user) {
                let self = this;
                self.$route.router.go({
                    path: '/user/edit/' + user.id
                });
            }
        },
        created() {
        }
    }
</script>
