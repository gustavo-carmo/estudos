<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Gerenciamento de produtos</title>
        <g:javascript library="jquery"></g:javascript>
    </head>
    <body>
        <g:link controller="produto" action="adicionar" update="form">Adicionar</g:link>
        <%= remoteLink(controller="produto", action: 'adicionar',
                update: 'success', onFailure: 'showError();')
                { "this is the link body" } %>
        <div id="lista">
            <g:render template="lista" model="[produtos: produtos]"></g:render>
        </div>

        <div id="form">
        </div>
    </body>
</html>
