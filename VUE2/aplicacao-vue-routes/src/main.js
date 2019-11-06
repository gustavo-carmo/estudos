import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const router = new VueRouter({
    routes: [
        {
            path: '/restaurante',
            component: require('./Restaurante/Restaurante.vue'),
            children: [
                {
                    path: 'yaksoba',
                    component: require('./Restaurante/Pratos/Yaksoba.vue')
                },
                {
                    path: 'sushi',
                    component: require('./Restaurante/Pratos/Sushi.vue')
                }
            ]
        },
        {
            path: '/pizzaria',
            component: require('./Pizzaria/Pizza.vue'),
            children: [
                {
                    path: '',
                    component: require('./Pizzaria/Pizzas/Default.vue')
                },
                {
                    path: 'calabresa',
                    component: require('./Pizzaria/Pizzas/Calabresa.vue')
                },
                {
                    path: '3queijos',
                    component: require('./Pizzaria/Pizzas/3queijos.vue')
                }
            ]
        }
    ]
});

new Vue({
    el: '#container',
    router,
    render: h => h(require('./App.vue'))
});