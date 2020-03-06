# estudos

######## PULL/PUSH ERROR ########

No GITHUB existem 2 formas de se fazer o pull/push de seus projetos uma delas é atraves do HTTPS que não existe nenhum tipo de restrição para se utilizar apenas o usuário e a senha do repositório, porem se seu intuito for utilizar o pull/push através do SSH será necessário criar um par de chaves no seu repositório do Git e no computador em que se deseja utilizar o projeto para mais informações a respeito da chave SSH acessar: https://medium.com/@rgdev/como-adicionar-uma-chave-ssh-na-sua-conta-do-github-linux-e0f19bbc4265

######## PULL/PUSH ERROR ########

######## ADD ERROR ########

Ao adicionar um arquivo com o comando add, pode ser que esse arquivo não apareça na lista de arquivos para comitar apresentando o erro "(modified content untracked content)". Para resolver esse erro você pode tentar limpar o cache do git nos arquivos com o erro, utilizando o comando -> "$ git rm -rf --cached <arquivo/pasta>" e em seguida adicionando os arquivos novamente.

######## ADD ERROR ########

######## COMANDOS ########

$ git init // Inicia o repositório do git
	Paramestros:
		--bare // Inicia um repositório de servidor do git que controla as alterações


$ git add . // Adiciona todos os arquivos para serem comitados


$ git commit -m "mensagem do commit" // Realiza o comit no git dos arquivos que foram adicionados


$ git log // Mostra todos commits com seus respectivos Hashs
	Parametros:
		--oneline // Informações do commit de forma resumida
		-p // Mostra os commits e as alterações que foram feitas


$ git config // É utilizado para configurar o usuário do git
	Paramestros:
		--local // Parametro para setar o usuário do git no projeto atual
		--global // Parametro para setar o usuário do git no escopo do computador
	Exemplo: 
		git config --global user.name "Gustavo Carmo"


$ git remote // Mostra o repositório remoto do git
	Parametros:
		rename // Renomeia o repositório
		add // Adiciona o repositório remoto
	Exemplo:
		git remote add <nome do repositório> <local do repositório (Pode ser um repositório local, na propia maquina ou um repositório remoto, informando uma URL)>


$ git branch <nome do branch> // Cria um branch 


$ git checkout <nome do branch> // Altera o Branch atual

$ git rebase <nome do branch> // Atualiza os comites para o branch selecionado e gera um novo comite atual 

$ git rever <Hash do commit> // Vai voltar o branch para o estado de antes do comite

$ git stash // Salva temporariamente as alterações
	Parametros:
		drop // Exclui o stash
		apply // vai para a stash desejada
		pop // vai para a ultima stash, atualiza o código para a stash e faz o drop 

$ git diff // Mostra a diferenças entre comites
	Exemplo:
		git diff <Hash-commit-1>..<Hash-commit-2>

$ git tag // Geera uma versão da aplicação
	Exemplo:
		git tag -a <nome-da-versao> -m "Commite da versão"
		git tag -d <nome-da-versao> // Remove uma Tag

-- ATENÇÃO  - BOA PRÁTICA NUNCA COMITAR UM CÓDIGO QUE NÃO FUNCIONA --

#####################################################################		
###		 PARA MAIS INFORMAÇÕES ACESSAR: https://devhints.io  	  ###
#####################################################################

######## COMANDOS ########
