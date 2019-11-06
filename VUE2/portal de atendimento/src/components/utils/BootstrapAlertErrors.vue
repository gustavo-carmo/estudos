<template>
    <div class="alert alert-danger" v-if="messages.length">
        <p v-for="message in messages">{{message}}</p>
    </div>
</template>
<script>
    export default {
        props: ["errors"],
        data() {
            return {
                messages: []
            }
        },
        watch: {
            'errors': function(val, oldVal) {

                let self = this;
                self.messages = [];

                var i;
                for (i in val) {
                    self.messages.push(val[i].message);
                }
            }
        }
    }
</script>
