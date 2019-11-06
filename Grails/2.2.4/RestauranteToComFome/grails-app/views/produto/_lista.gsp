<g:if test="${produtos.size() > 0}">
    <table>
        <tr>
            <th>Nome</th>
            <th>Preço</th>
            <th>Qtde. Atual</th>
            <th>Qtde. Minima</th>
            <th>Ações</th>
        </tr>
        <g:each var="produto" in="${produtos}">
            <tr>
                <td>${produto.nome}</td>
                <td>${produto.preco}</td>
                <td>${produto.estoque?.quantidade}</td>
                <td>${produto.estoque?.quantidadeMinima}</td>
                <td>
                    <g:remoteLink controller="produto" action="alterar" update="form" id="${produto.id}">Alterar</g:remoteLink>
                    &nbsp;
                    %{--<g:remoteLink controller="produto" action="excluir" update="lista" id="${produto.id}">Excluir</g:remoteLink>--}%
                    <a href="#" onclick="excluir('${produto.id}')">Excluir</a>
                </td>
            </tr>
        </g:each>
    </table>
</g:if>
<g:else>
    Não há produtos cadastrados!
</g:else>