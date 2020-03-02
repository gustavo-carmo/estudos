<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Projeto Login</title>
  <link rel="stylesheet" href="./css/style.css">
</head>
<body>
  
  <main>

    <section id="formulario">
      <h1>Entrar</h1>
      <form method="POST" action="processar.php">
        <input type="email" placeholder="Usuário">
        <input type="password" placeholder="Senha">
        <input type="submit" value="ACESSAR">

        <a href=".cadastrar.php">Ainda não é inscrito?<strong>Cadastre-se</strong></a>
      </form>
    </section>
  </main>
</body>
</html>