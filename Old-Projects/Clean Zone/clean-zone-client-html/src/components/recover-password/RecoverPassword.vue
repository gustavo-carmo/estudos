<template>
    <div id="cl-wrapper" class="login-container">
        <div class="middle-login">
            <div class="block-flat">
                <div class="header">
                    <h3 class="text-center"><img src="../../../static/img/logo.png" alt="logo" class="logo-img">Clean Zone</h3>
                </div>
                <div>
                    <div class="content">

                        <form style="margin-bottom: 0px !important;" class="form-horizontal" v-if="!success">
                            <div class="alert alert-danger" v-if="errors">
                                <p v-for="error in errors">
                                    {{error.message}}
                                </p>
                            </div>
                            <h4 class="title">Recuperar Senha</h4>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                        <input id="nova-senha" type="password" placeholder="Nova senha" class="form-control" v-model="password">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                        <input id="repita-senha" type="password" placeholder="Repita senha" class="form-control" v-model="repeatPassword">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button id="salvar" type="button" class="btn btn-primary pull-right" @click.prevent="save" :disabled="valid">
                                        Enviar
                                    </button>
                                    <a v-link="{ path: '/blank/login' }" class="btn pull-right">Cancelar</a>
                                </div>
                            </div>
                        </form>

                        <div class="text-center margin-bottom-40" v-if="success">
                            <div class="i-circle success">
                                <i class="fa fa-check"></i>
                            </div>
                            <h4>
                                <strong>Sucesso!</strong>
                            </h4>
                            <p>Sua senha foi alterada com sucesso</p>

                            <p>
                                <a v-link="{ path: '/blank/login' }" class="padding-top-15">
                                Clique aqui para voltar para o login
                            </a>
                            </p>
                        </div>

                    </div>

                </div>
            </div>
            <div class="text-center out-links">
                <a href="#">© 2016 Your Company</a>
            </div>
        </div>
    </div>
</template>

<script>
    import AppConfig from '../misc/AppConfig.vue'

    const CHANGE_PASSWORD_TOKEN_URL = AppConfig.apiUrl() + '/change-password-by-token'

    export default {
        data() {
            return {
                password: '',
                repeatPassword: '',
                errors: null,
                token: '',
                success: false
            }
        },
        computed: {
            isValidToSubmit: function () {
                return this.password && this.repeatPassword
            }
        },
        methods: {
            save() {
                let t = this
                if (this.password === this.repeatPassword) {

                    t.token = t.$route.params.token

                    this.$http.put(CHANGE_PASSWORD_TOKEN_URL, JSON.stringify({
                        "token": this.token,
                        "password": this.password

                    })).then(function (response) {
                                let resp = response.body


                                if (resp.meta.code === 400) {

                                    t.errors = resp.errors
                                } else {

                                    t.success = true
                                }
                            }, function (error) {
                                t.errors = [{'message': 'Ocorreu um erro inesperado!'}]
                            }
                    );
                } else {

                    this.errors = [{
                        "message": 'As senhas são diferentes'
                    }]
                }
            }
        }
    }
</script>
