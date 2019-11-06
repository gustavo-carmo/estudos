<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Gerenciamento de produtos</title>
        <g:javascript library="jquery"></g:javascript>

        <script type="text/javascript">
        function carregarLista() {
            <g:remoteFunction controller="produto" action="lista" update="lista" />
        }

        function excluir(id) {
            console.log("id -> ", id);
            if(confirm("Deseja excluir o produto?")) {
                <g:remoteFunction controller="produto" action="excluir" update="lista" id="'+id+'" ></g:remoteFunction>
            }
        }

        function cancelar() {
            jQuery("#form").html("");
        }
        </script>
    </head>
    <body>

        <g:remoteLink controller="produto" action="adicionar" update="form">Adicionar</g:remoteLink>
        <div id="lista">
            <g:render template="lista" model="[produtos: produtos]"></g:render>
        </div>

        <div id="form">
        </div>
    </body>
</html>
