<?php

function exibeTitulos($mensagem) {
  echo "$mensagem" . PHP_EOL;
}

exibeTitulos("Teste 1");
exibeTitulos("Teste 2");
exibeTitulos("Adriano é Mito");

function somar($num1, $num2) {
  
  return $num1 + $num2;
}

exibeTitulos("Somando 4 + 7");
exibeTitulos(somar(4, 7));

// O tipo de retorno no PHP é informado após os 2 pontos
function soRecebeValorFloat(float $bla): float {
  return $bla * 0.78;
}



?>