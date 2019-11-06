<template>
    <div id="cl-wrapper" class="login-container">
        <div class="middle-login">
            <div class="block-flat">
                <div class="header">
                    <h3 class="text-center">
                        <img src="../../../static/img/logo.png" alt="logo" class="logo-img">Clean Zone
                    </h3>
                </div>
                <div>
                    <form style="margin-bottom: 0px !important;" class="form-horizontal">
                        <div class="content">
                            <div class="alert alert-danger" v-if="error">
                                <button type="button" aria-hidden="true" class="close" @click.prevent="cleanError">×</button>
                                <i class="fa fa-times-circle sign"></i>{{error}}
                            </div>
                            <h4 class="title">Login</h4>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input id="username" type="text" placeholder="Nome de usuário" class="form-control" v-model="username">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <div class="input-group"><span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                        <input id="password" type="password" placeholder="Senha" class="form-control" v-model="password">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="foot">
                            <a v-link="{ path: '/blank/forgotten-password' }" class="pull-left padding-top-15">Esqueci a senha</a>
                            <button type="button" class="btn btn-primary" @click.prevent="login" :disabled="!isValidToSubmit">Entrar</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="text-center out-links"><a href="#">© 2016 Your Company</a></div>
        </div>
    </div>
</template>

<script>
    import Auth from '../misc/Auth.vue'

    export default {
        data() {
            return {
                error: '',
                username: '',
                password: ''
            }
        },
        computed: {
            isValidToSubmit: function () {
                return this.username && this.password
            }
        },
        methods: {
            cleanError() {
                this.error = ''
            },
            login() {
                Auth.login(this, JSON.stringify({
                    "username": this.username,
                    "password": this.password
                }), "/");
            }
        }
    }
</script>
