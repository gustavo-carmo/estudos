<template>


    <div v-if="title">
        <div class="page-head">
            <ol class="breadcrumb pull-right padding-top-15">
                <li><a href="#" v-link="{ path: '/index'}">Home</a></li>
                <!--<li><a href="#">Category</a></li>-->
                <li class="active">{{title}}</li>
            </ol>
            <h2>{{title}}</h2>
        </div>
    </div>

    <div class="cl-mcont">

        <slot></slot>

    </div>


</template>
<script>
    export default {
        props: ["title"],
        data() {
            return {}
        }
    }
</script>
