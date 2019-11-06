<script>
   export default{
        props: ["loginField"],
        methods: {
            resolveLoginField() {

              let labels = {
                'USERNAME': 'Nome de Usuário',
                'DOCUMENT_NUMBER': 'Numero de Documento'
              }

              return labels[this.loginField]
            }
        }
    }
</script>

<template>
    <span>
      {{resolveLoginField()}}
    </span>
</template>
