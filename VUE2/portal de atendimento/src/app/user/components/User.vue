<template>
  <module-template :title="resolveTitle()" :subtitle="subtitle">
    <router-view></router-view>
  </module-template>
</template>

<script>
    import ModuleTemplate from 'components/root/main/ModuleTemplate.vue'

    export default{
        props: ['appendToTitle', 'subtitle'],
        data () {
            return {
                title: 'Usuário'
            }
        },
        methods: {
            resolveTitle() {
                if (this.appendToTitle) {
                    return this.title + " - " + this.appendToTitle;
                } else {
                    return this.title;
                }
            }
        },
        components: {
            ModuleTemplate
        }
    }
</script>
