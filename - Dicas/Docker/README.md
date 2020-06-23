https://docs.docker.com/install/linux/docker-ce/ubuntu/

Para fazer a instalação do docker utilize o link abaixo para adicionaro repositório do docker no elementary e fazer a instalação
https://unix.stackexchange.com/questions/363048/unable-to-locate-package-docker-ce-on-a-64bit-ubuntu

após instalar o docker seguir os comandos abaixo para adicionar seu usuário ao grupo de acesso ao docker

$ sudo groupadd docker
$ sudo usermod -aG docker ${USER}
$ su -l ${USER}
