<template>
    <div class="row pagination-container" v-if="pagination.totalCount">
        <div class="col-sm-4 total-count">
            Exibindo: <b>{{from}}</b> até <b>{{to}}</b> registros de <b>{{pagination.totalCount }} </b>
        </div>
        <div class="col-sm-8">
            <ul class="pagination pagination-sm pull-right">
                <li>
                    <a class="button" @click="goPreviousPage()" v-if="pagination.totalPage != 1 && pagination.page != 1">Anterior</a>
                    <span class="button" v-if="pagination.totalPage == 1 || pagination.page == 1">Anterior</span>
                </li>
                <li>
                    <a class="button" v-show="pagination.totalPage != 1" v-if="pagination.page != 1" @click="goFirstPage()">1</a>
                    <span class="button" v-show="pagination.totalPage != 1" v-if="pagination.page == 1">1</span>
                </li>
                <li>
                    <span>...</span>
                </li>
                <li>
                    <a class="button" v-show="showPreviousButton" @click="goPage(pagination.page-1)">{{pagination.page-1}}</a>
                </li>
                <li>
                    <span class="button is-primary">{{pagination.page}}</span>
                </li>
                <li>
                    <a class="button" v-show="showNextButton" @click="goPage(pagination.page+1)">{{pagination.page+1}}</a>
                </li>
                <li>
                    <span>...</span>
                </li>
                <li>
                    <a class="button" v-show="pagination.totalPage != 1" v-if="pagination.totalPage != pagination.page" @click="goLastPage()">{{totalPages}}</a>
                    <span class="button" v-show="pagination.totalPage != 1" v-if="pagination.totalPage == pagination.page">{{totalPages}}</span>
                </li>
                <li>
                    <a class="button" @click="goNextPage()" v-if="pagination.totalPage != pagination.page">Próximo</a>
                    <span class="button" v-if="pagination.totalPage == pagination.page">Próximo</span>
                </li>
            </ul>
        </div>
    </div>
</template>
<style>
    div.pagination-container div.total-count {
        margin-top: 10px;
    }
</style>
<script>
    export default{
        props: ["pagination"],
        computed: {
            totalPages: function () {
                return this.pagination.totalPage
            },
            showNextButton: function () {
                return this.pagination.page != this.totalPages
            },
            showPreviousButton: function () {
                return this.pagination.page != 1
            },
            from() {
                let self = this
                return self.pagination.page * self.pagination.perPage - self.pagination.perPage + 1
            },
            to() {
                let self = this
                return (self.pagination.page * self.pagination.perPage) < self.pagination.totalCount ? (self.pagination.page * self.pagination.perPage) : self.pagination.totalCount
            }
        },
        methods: {
            goNextPage: function () {
                this.$emit('change-page', this.pagination.page + 1)
            },
            goPreviousPage: function () {
                this.$emit('change-page', this.pagination.page - 1)
            },
            goFirstPage: function () {
                this.$emit('change-page', 1)
            },
            goLastPage: function () {
                this.$emit('change-page', this.totalPages)
            },
            goPage: function (page) {
                this.$emit('change-page', page)
            }
        }
    }
</script>
