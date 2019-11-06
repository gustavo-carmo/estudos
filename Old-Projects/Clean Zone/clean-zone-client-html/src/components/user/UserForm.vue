<template>
    <!--<div class="block-flat">-->
    <tabs>
        <tab :header="titleForm">
            <bootstrap-alert-errors :errors="errors"></bootstrap-alert-errors>

            <form>
                <input type="hidden" v-model="user.id">
                <input type="hidden" v-model="user.version">

                <div class="row">

                    <div class="col-sm-3">
                        <div class="form-group">
                            <label>Nome</label>
                            <input type="text" class="form-control"
                                   v-model="user.name" :class="{ 'parsley-error' : errors.name }">
                        </div>
                    </div>

                    <div class="col-sm-3">
                        <div class="form-group">
                            <label>Nome de usuário</label>
                            <input type="text" class="form-control"
                                   v-model="user.username" :class="{ 'parsley-error' : errors.username }">
                        </div>
                    </div>

                    <div class="col-sm-3">
                        <div class="form-group">
                            <label>Email</label>
                            <input type="text" class="form-control"
                                   v-model="user.email" :class="{ 'parsley-error' : errors.email }">
                        </div>
                    </div>

                    <div class="col-sm-3">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label class="control-label">Enabled</label>
                                <input type="checkbox" v-model="user.enabled">
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row">

                    <div class="col-sm-3">
                        <div class="form-group">
                            <label>Senha</label>
                            <input type="password" class="form-control"
                                   v-model="user.password" :class="{ 'parsley-error' : errors.password }">
                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <button class="btn btn-primary" @click.prevent="save">Salvar</button>
                            <button class="btn btn-default" @click.prevent="backToSearch">Voltar para pesquisa</button>
                            <button class="btn btn-default" @click.prevent="show(user)" v-if="user.id">Voltar para detalhes</button>
                        </div>
                    </div>

                </div>

            </form>
        </tab>
    </tabs>
    <!--</div>-->
</template>
<script>

    import UserService from './UserService.vue';
    import Utils from '../utils/Utils.vue';

    export default{
        data(){
            return {
                user: {},
                errors: [],
                fieldsWithErrors: [],
                titleForm: 'Novo'
            }
        },
        ready() {
        },
        created() {
            let self = this;
            var id = self.$route.params.id;
            if (id) {
                UserService.getUser(
                        self.$http,
                        id,
                        function (data) {
                            self.user = data;
                        }
                );
                self.titleForm = 'Editar';
            }
        },
        components: {},
        computed: {},
        methods: {
            backToSearch: function () {
                let self = this;
                self.$route.router.go({
                    path: '/user'
                });
            },
            save: function () {
                // loading
                // é um novo user
                let self = this;
                UserService.saveUser(
                        self.$http,
                        self.user,
                        function (user) {
                            self.$route.router.go({
                                path: '/user/show/' + user.id
                            });
                            Utils.notifySuccessMessage(user.id, 'Usuário');
                        }, function (error) {
                            self.errors = error;
                        }
                );
                // http method = POST
                // uri = users
                // é uma atualização
                // http method = PUT
                // uri = users/id
                // request AJAX para servidor
                // sucess
                // request AJAX para servidor GET $id
                // this.user = response.user
                // exibir detalhes
                // fim loading
                // error
                // erro de validao
                // fica no form
                // exibir os erros
                // fim loading
                // outro erro
                // exibir error inesperado
                // fim loading

//                this.user.enabled = this.user.enabled == 'true';
//                this.user.creationDate = new Date();
//                this.users.push(this.user);

                // this.show(this.user);
                // redirect t0 show
            },
            show: function (user) {
                let self = this;
                self.$route.router.go({
                    path: '/user/show/' + user.id
                });
            }
        }
    }
</script>
