<template>
    <div id="cl-wrapper" class="forgotpassword-container">
        <div class="middle">
            <div class="block-flat">
                <div class="header">
                    <h3 class="text-center"><img src="../../../static/img/logo.png" alt="logo" class="logo-img"> Clean Zone</h3>
                </div>
                <div class="content">

                    <form style="margin-bottom: 0px !important;" class="form-horizontal" v-if="exibir === 'formulario'">
                        <div class="alert alert-danger" v-if="errors">
                            <p v-for="error in errors">
                                {{error.message}}
                            </p>
                        </div>
                        <h5 class="title text-center"><strong>Esqueceu sua senha?</strong></h5>
                        <p class="text-center">
                            Não se preocupe, nós lhe enviaremos um e-mail para redefinir sua senha
                        </p>
                        <hr>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                    <i class="fa fa-envelope"></i>
                                </span>
                                    <input name="username" parsley-trigger="change"
                                           parsley-error-container="#email-error" required=""
                                           placeholder="Nome de usuario"
                                           class="form-control" v-model="username">
                                </div>
                                <div id="email-error"></div>
                            </div>
                        </div>

                        <div class="foot">

                            <a v-link="{ path: '/blank/login' }" class="btn">Cancelar</a>
                            <button type="button" class="btn btn-primary" @click.prevent="send"
                                    :disabled="!isValidToSubmit">Enviar
                            </button>

                        </div>
                    </form>

                    <div class="text-center margin-bottom-40" v-if="exibir === 'mensagemSucesso'">
                        <div class="i-circle success"><i class="fa fa-check"></i></div>
                        <h4><strong>Sucesso!</strong></h4>
                        <p>Foi enviado um email para o email informado, por favor verifique sua caixa de entrada!</p>

                        <p>
                            <a v-link="{ path: '/blank/login' }" class="padding-top-15">
                                Clique aqui para voltar para o login
                            </a>
                        </p>
                    </div>

                </div>
            </div>
            <div class="text-center out-links"><a href="#">© 2016 Your Company</a></div>
        </div>
    </div>
</template>

<script>
    import AppConfig from '../misc/AppConfig.vue'

    const RETRIEVE_PASSWORD_URL = AppConfig.apiUrl() + '/reset-password'

    export default {

        data() {
            return {
                exibir: 'formulario',
                errors: null,
                username: '',
                PATH_LOGIN: '/blank/login'
            }
        },
        computed: {
            isValidToSubmit: function () {
                return this.username
            }
        },
        methods: {
            send() {

                this.$http.post(RETRIEVE_PASSWORD_URL,
                        JSON.stringify({"username": this.username})).then(function (response) {


                            let resp = response.body;


                            if (resp.meta.code === 400) {

                                this.errors = response.body.errors
                            } else {

                                this.exibir = 'mensagemSucesso'
                            }
                        }, function (error) {
                            this.errors = [{'message': 'Usuario invalido'}]
                        }
                );
            }
        }
    }
</script>
