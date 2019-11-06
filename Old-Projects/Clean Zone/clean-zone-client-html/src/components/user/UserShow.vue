<template>

    <div class="tab-container">

        <tabs>
            <tab header="Detalhes">
                <div class="tab-pane cont active form-group-border-bottom-dashed" id="details">

                    <div class="row">

                        <div class="col-sm-3">
                            <div class="form-group">
                                <label>Nome</label>
                                <p>{{user.name}}</p>
                            </div>
                        </div>

                        <div class="col-sm-2">
                            <div class="form-group">
                                <label>Nome de usuário</label>
                                <p>{{user.username}}</p>
                            </div>
                        </div>

                        <div class="col-sm-2">
                            <div class="form-group">
                                <label>Email</label>
                                <p>{{user.email}}</p>
                            </div>
                        </div>

                        <div class="col-sm-2">
                            <div class="form-group">
                                <label class="control-label">Ultimo login</label>
                                <p><i class="fa fa-calendar"></i> {{ user.lastLogin | moment "DD/MM/YYYY" }} </p>
                            </div>
                        </div>

                        <div class="col-sm-2">
                            <div class="form-group">
                                <label class="control-label">Data de criação</label>
                                <p><i class="fa fa-calendar"></i>
                                    {{ user.createdDate | moment "DD/MM/YYYY" }}
                                </p>
                            </div>
                        </div>

                        <div class="col-sm-1">
                            <div class="form-group">
                                <label class="control-label">Habilitado</label>
                                <p>
                                <span class="label" :class="{ 'label-success' : user.enabled, 'label-danger' : !user.enabled }">
                                    {{ user.enabled | formatBoolean }}
                                </span>
                                </p>
                            </div>
                        </div>

                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <button class="btn btn-primary" @click.prevent="edit(user)">Editar</button>
                            <button class="btn btn-default" @click.prevent="backToSearch">Voltar para pesquisa</button>
                        </div>

                    </div>

                </div>
            </tab>
            <tab header="Permissões">
                <div class="tab-pane cont" id="permissions">

                    <div class="row" v-for="role in roles">
                        <div class="form-group">
                            <div class="col-sm-12">
                                <label class="checkbox-inline">
                                    <input type="checkbox" :checked="role.enabled" v-model="role.enabled" name="check1"> {{role.name}}
                                </label>
                            </div>
                        </div>
                    </div>

                    <!--<div class="row">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label class="checkbox-inline">
                                    <input type="checkbox" checked="" name="check1"> Cliente
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="check1"> Úsuarios
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="check1"> produtos
                                </label>
                            </div>
                        </div>
                    </div>-->

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <button class="btn btn-primary" @click.prevent="savePermissions()">Salvar</button>
                                <button class="btn btn-default" @click.prevent="backToSearch">Voltar para pesquisa</button>
                            </div>
                        </div>
                    </div>

                </div>
            </tab>
        </tabs>

    </div>
</template>
<script>

    import UserService from './UserService.vue'
    import RoleService from '../role/RoleService.vue'
    import Utils from '../utils/Utils.vue'

    export default{
        data(){
            return {
                user: {},
                roles: [],
                userRoles: []
            }
        },
        components: {},
        created() {
            let self = this;
            let id = self.$route.params.id
            UserService.getUser(
                    self.$http,
                    id,
                    function (data) {

                        self.user = data;

                        UserService.findUserRoles(
                                self.$http,
                                id,
                                function (data) {
                                    self.userRoles.push(data.id);
                                },
                                function (data) {
                                },
                                function () {
                                    RoleService.searchRole(
                                            self.$http,
                                            {},
                                            function (data) {
                                                self.roles.push({
                                                    id: data.id,
                                                    name: data.name,
                                                    enabled: Utils.contains(self.userRoles, data.id)
                                                });
                                            },
                                            function (data) {

                                            }
                                    );
                                }
                        );
                    }
            );
        },
        methods: {
            backToSearch: function () {
                let self = this;
                self.$route.router.go({
                    path: '/user'
                });
            },
            edit: function (user) {
                let self = this;
                self.$route.router.go({
                    path: '/user/edit/' + user.id
                });
            },
            savePermissions: function () {
                let self = this;
                let id = self.$route.params.id;
                let paramRoles = [];

                self.roles.forEach(function (data) {
                    if (data.enabled) {
                        paramRoles.push(data.id);
                    }
                });

                UserService.saveUserRoles(
                        self.$http,
                        id,
                        paramRoles,
                        function () {
                            Utils.notifySuccess('Permissões alteradas com sucesso!');
                        },
                        function (error) {
                            Utils.buildErrorMessage(error);
                        }
                );
            },
        }
    }
</script>
