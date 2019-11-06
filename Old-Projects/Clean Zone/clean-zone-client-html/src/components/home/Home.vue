<template>

    <nav-bar></nav-bar>

    <div id="cl-wrapper" class="sb-collapsed">

        <!--<side-bar></side-bar>-->

        <div id="pcont" class="container-fluid">
            <router-view></router-view>
        </div>

    </div>
</template>

<script>
    import NavBar from '../utils/NavBar.vue'
    import SideBar from '../utils/SideBar.vue'

    export default {
        components: {
            NavBar,
            SideBar
        },
        ready() {
            /*this.$router.go("/index");*/
        }
    }
</script>
