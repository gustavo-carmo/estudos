<template>
  <div class="hello">
    <hr>
    {{bla}}
    <hr>
  </div>
</template>

<script>
import boletoValidator from 'boleto-validator'

export default {
  name: 'HelloWorld',
  data () {
    return {
      bla: 'dsçlfajflç'
    }
  },
  methods: {
    moduloOnze: function(numero) {
      let modulo = 0;
      let peso = 2;
      let digito = 0;
      let total = 0;
      let contador = numero.length - 1;
      console.log("Setei as variaveis!");
      while (contador >= 0){
           console.log("total -> ", total);
           console.log("contador -> ", contador);
           console.log("numero.substring(1, contador) -> ", numero.substring(contador, contador + 1));
           total += parseInt(numero.substring(contador, contador + 1)) * peso;
           contador--;
           peso++;
      }
      console.log("Termino do while!");
      if (total >= 11){
         console.log("Primeiro IF!");
         modulo = total % 11;
         if (modulo > 1){
            digito = 11 - modulo;
         }else{
            if (modulo == 1){
               digito = 0;
            }else{
               if (modulo == 0){
                  digito = 1;
               }
            }
         }
      }else{
         console.log("ELSE!");
         digito = 11 - total;
      }
      return digito;
    }
    /*
    public int modulo11(String numero){
      int *peso, *total, contador, modulo, *digito;
      digito = 0;
      peso     = 2;
      total    = 0;
      contador = numero.length()-1;
      while (contador >= 0){
         total += Integer.parseInt(numero.substring(contador,1)) * peso;
         contador--;
         peso++;
      }
      if (total >= 11){
         modulo = total % 11;
         if (modulo > 1){
            digito = 11 - modulo;
         }else{
            if (modulo == 1){
               digito = 0;
            }else{
               if (modulo == 0){
                  digito = 1;
               }
            }
         }
      }else{
         digito = 11 - total;
      }
      return digito;
    }
    */
  },
  mounted: function () {
    /*
    boletoValidator.convenio("83640000001-1 33120138000-2 81288462711-6 08013618155-1", (err, isValid) => {
        if(isValid) {
          this.bla = "Boleto de convenio... É valido!";
        } else {
          this.bla = "Erro, insira outro código de boleto!";
        }
    });
    */

    //boletoValidator.boleto("42297.11504 00001.954411 60020.034520 2 68610000054659", (err, isValid) => {
    //TODO peguei na internet, é para funcionar com o module 11 ou 10
    //boletoValidator.boleto("00190.00009 00502.010184 00016.324188 6 335100000 15000", (err, isValid) => {
    //boletoValidator.boleto("10491.92006 00019.231786 00000.001131 3 38150000000633", (err, isValid) => {
    //10491920060001923178600000001131338150000000633
    //23791.11103 60000.000103 01000.222206 1 48622000000000
    boletoValidator.boleto("23791.11103 60000.000103 01000.222206 1 48622000000000", (err, isValid) => {
        if(isValid) {
          this.bla = "Boleto de Fatura/Carnê... É valido!";
        } else {
          this.bla = "Erro, insira outro código de boleto!";
        }
    });
    //console.log(this.moduloOnze("10490"));
  }
}
</script>

<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
