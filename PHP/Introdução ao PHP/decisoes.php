<?php

$idade = 18;
$idade2 = '18';

echo "Você só pode entrar se tiver mais que 18 anos" . PHP_EOL;

IF ($idade == 18) {
  echo "Você tem $idade anos. Pode Entrar" . PHP_EOL;
}

IF ($idade2 === 18) {
  echo "Idade 2 em int é igual a 18 anos";
}

echo "Teste";