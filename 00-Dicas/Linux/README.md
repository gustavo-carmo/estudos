##### ESPAÇO NO LINUX #####


No linux pode ser que em algum momento você vá precisar apagar alguns arquivos para obter mais espaço em disco.
Para identificar a quantidade de espaço que você tem em cada pasta siga os seguintes passos
1 - logue no terminal como o super usuário "$ sudo su"
2 - vá no diretório raiz "/"
3 - use o comando "du -sch * | sort -h" para saber quanto de espaço tem em cada pasta
4 - procure as pastas que você deseja excluir e utilize o comando "rm -rf"


##### ESPAÇO NO LINUX #####

##### INSTALAÇÃO #####

/**** ARQUIVOS .DEB ****/
1 - abra o terminal 
2 - vá na pasta do arquivo
3 - executar o comando
    $ sudo dpkg -i <nome do arquivo>.deb 

#Ambiente Pantheon(Desktop do Linux elementary) no Ubuntu
https://www.edivaldobrito.com.br/ambiente-pantheon-no-ubuntu/
https://www.edivaldobrito.com.br/ambiente-pantheon-no-ubuntu-18-04/

##### INSTALAÇÃO #####

##### MATAR UM SERVIÇO RODANDO EM ALGUMA PORTA #####

$ fuser -n tcp <numero da porta>

##### MATAR UM SERVIÇO RODANDO EM ALGUMA PORTA #####

##### COPIAR ARQUIVO DE UM OUTRO COMPUTADOR (SSH/SCP) #####

syntax:
$ scp <Arquivo> <Destino>

Para copiar de B para o A enquanto logado no B (Enviar um arquivo - Upload)
$ scp /path/to/file username@a:/path/to/destination

Para copiar de B para o A enquanto logado no A (Baixar um arquivo - Download)
$ scp username@b:/path/to/file /path/to/destination

Caso deseije passar uma porta 
$ scp -P <Porta> <Arquivo> <Destino>

##### COPIAR ARQUIVO DE UM OUTRO COMPUTADOR (SSH/SCP) #####