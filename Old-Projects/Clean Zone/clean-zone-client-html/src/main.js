import Vue from 'vue';
import VueRouter from 'vue-router';
import VueResource from 'vue-resource';

Vue.use(VueRouter);
Vue.use(VueResource);
Vue.use(require('vue-moment')); // http://momentjs.com/

import VueStrap from 'vue-strap';
Vue.component('tabs', VueStrap.tabset);
Vue.component('tab', VueStrap.tab);
Vue.component('datepicker', VueStrap.datepicker);

require('../static/css/style.css');
require('../static/pa-css/pa.css');

/*var $ = require('jquery');
var jQuery = require('jquery');*/
require('bootstrap');
require('gritter');

import PageModule from './components/utils/PageModule.vue'
Vue.component('page-module', PageModule);

import BootstrapAlertErrors from './components/utils/BootstrapAlertErrors.vue'
Vue.component('bootstrap-alert-errors', BootstrapAlertErrors);

import App from './components/App.vue'
import Auth from './components/misc/Auth.vue'
// import Login from './login/Login.vue'
// import ForgottenPassword from './forgotten-password/ForgottenPassword.vue'
// import RecoverPassword from './recover-password/RecoverPassword.vue'
// import Home from './home/Home.vue'
// import Profile from './profile/Profile.vue'
import User from './components/user/User.vue'

const router = new VueRouter();

Auth.checkAuth();

// Auth.addAuthorizationHeader(Vue);
// Vue.http.headers.common['Authorization'] = 'Basic YXBpOnBhc3N3b3Jk';
// Vue.http.headers.common['Authorization'] = Auth.getAuthHeader();

Vue.filter('formatBoolean', function (value) {
    return value ? 'Sim' : 'Não';
});


router.map({
    '/blank': {
        component: {
            template: "<router-view></router-view>"
        },
        subRoutes: {
            '/login': {
                component: require('./components/login/Login.vue')
            },
            '/forgotten-password': {
                component: require('./components/forgotten-password/ForgottenPassword.vue')
            },
            '/recover-password/:token': {
                component: require('./components/recover-password/RecoverPassword.vue')
            }
        }
    },
    '/': {
        component: require('./components/home/Home.vue'),
        auth: true,
        subRoutes: {
            '/index': {
                component: {
                    template: '<page-module></page-module>'
                }
            },
            '/profile': {
                component: require('./components/profile/Profile.vue')
            },
            '/user': {
                component: User,
                role: 'ROLE_USER',
                subRoutes: User.subRoutes()
            },
            '/customer': {
                component: {
                    template: '<h1>customer'
                },
                role: 'ROLE_CUSTOMER'
            },
            '/denied': {
                component: {
                    template: '<h1>Access denied....</h1>'
                }
            }
        }
    },
    '*': {
        component: {
            template: '<h1>Not found....</h1>'
        }
    }
});

Vue.http.interceptors.push((request, next) => {
    next((response) => {
        if (response.status != 200) {
            console.log(response.status != 200);
            console.log(response.status);
        }
        if (response.status == 401 || response.status == 0) {
            console.log('response.status');
            console.log(response.status);
            console.log('redirect to login');
            console.log(response.status);
            Auth.logout(router);
        }
    });
});

Auth.validateAccess(router);

router.start(App, '#container')
