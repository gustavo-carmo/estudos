<?php

// MAPAS

$counta = [
  'titular' => 'Maria',
  'saldo' => 10000
];

$counta1 = [
  'titular' => 'João',
  'saldo' => 1000
];

$counta2 = [
  'titular' => 'Helton',
  'saldo' => 800
];

$listContas = [ $counta, $counta1, $counta2 ];

for ($i = 0; $i < count($listContas); $i++ ) {
  echo "Titular: " . $listContas[$i]['titular'] . ", saldo: " . $listContas[$i]['saldo'] . PHP_EOL;
}

echo PHP_EOL . PHP_EOL;
echo "Lista com Foreach -> " . PHP_EOL;

$listaContasForEach = $listContas;

foreach($listaContasForEach as $conta) {
  echo "Titular: " . $conta['titular'] . ", saldo: " . $conta['saldo'] . PHP_EOL;
}

$listContasComChave = [
  '324.432.236-43' => [
    'titular' => 'Maria',
    'saldo' => 10000
  ],
  '432.122.126-98' => [
    'titular' => 'João',
    'saldo' => 1000
  ],
  '853.495.993-99' => [
    'titular' => 'Helton',
    'saldo' => 800
  ]
];

echo PHP_EOL . PHP_EOL;
echo "Lista Mapa antes de debitar -> " . PHP_EOL;

foreach($listContasComChave as $conta) {
  echo "Titular: " . $conta['titular'] . ", saldo: " . $conta['saldo'] . PHP_EOL;
}

$listContasComChave['853.495.993-99']['saldo'] -= 5900;

echo PHP_EOL;
echo "Lista Mapa após de debitar -> " . PHP_EOL;

foreach($listContasComChave as $conta) {
  echo "Titular: " . $conta['titular'] . ", saldo: " . $conta['saldo'] . PHP_EOL;
}