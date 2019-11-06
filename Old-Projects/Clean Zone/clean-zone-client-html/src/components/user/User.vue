<template>
    <page-module :title="pageTitle">

        <router-view></router-view>

    </page-module>
</template>


<script>

    import UserService from './UserService.vue'

    export default {
        subRoutes() {
            return {
                '/': {
                    component: require('./UserList.vue')
                },
                '/create': {
                    component: require('./UserForm.vue')
                },
                '/edit/:id': {
                    component: require('./UserForm.vue')
                },
                '/show/:id': {
                    component: require('./UserShow.vue')
                }
            }
        },
        data() {
            return {
                pageTitle: 'Usuários'
            }
        },
        methods: {},
        components: {},
        ready() {
        }
    }
</script>
