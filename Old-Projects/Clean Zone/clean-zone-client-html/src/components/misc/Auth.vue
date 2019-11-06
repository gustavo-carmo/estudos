<script>

    import AppConfig from './AppConfig.vue'

    const LOGIN_URL = AppConfig.apiUrl() + '/api/login';
    const PATH_LOGIN = '/blank/login';

    export default {

        user: {
            authenticated: false,
            username: '',
            roles: [],
            token: ''
        },

        addAuthorizationHeader(Vue) {
            if (this.user.token) {
                console.log({'Authorization': 'Bearer ' + this.user.token});
                Vue.http.headers.common['Authorization'] = 'Bearer ' + this.user.token;
            } else {
                console.log("No token...");
            }
        },

        getAuthorizationHeader() {
            if (this.user.token) {
                return {'Authorization': 'Bearer ' + this.user.token}
            } else {
                return {}
            }
        },

        checkAuth() {
            if (localStorage.authenticated) {
                let authenticatedData = JSON.parse(localStorage.authenticated);
                this.user.authenticated = true;
                this.user.username = authenticatedData.username;
                this.user.roles = authenticatedData.roles;
                this.user.token = authenticatedData.access_token;
            }
        },

        login(context, creds, redirect) {

            let self = this;
            context.$http.post(LOGIN_URL, creds).then(function (response) {

                localStorage.setItem('authenticated', JSON.stringify(response.body));

                self.checkAuth();

                if (redirect) {
                    context.$router.go(redirect);
                }
            }, function (response) {
                context.error = 'Seu usuário ou senha estão incorretos.';
            }).finally(function () {
                // console.log('fim.......login');
            });

        },

        logout(router) {
            localStorage.removeItem('authenticated')
            this.user = {
                authenticated: false,
                username: '',
                roles: [],
                token: ''
            }
            // TODO - não redireciona quando está em '/'
            // route.redirect(PATH_LOGIN);
            location.reload();
        },

        getUsername() {
            return this.user.username;
        },

        validateAccess(router) {

            let self = this;
            router.beforeEach(function (route) {
                // console.log('path [' + route.to.path + '] auth required: [' + (route.to.auth ? true : false) + ']');
                if (route.to.auth) {
                    if (!self.user.authenticated) {
                        console.log('no authenticated user, go to login page');
                        route.redirect(PATH_LOGIN);
                    } else if (route.to.role && self.user.roles.indexOf(route.to.role) < 0) {
                        console.log('has authenticated [' + self.user.username + '] user, but no role [' + route.to.role + '], user roles [' + self.user.roles + '] ');
                        route.redirect('/denied');
                    }
                    route.next();
                } else {
                    route.next();
                }
            });
        }
    }
</script>
