import React from 'react';
import logo from './logo.svg';
import './App.css';
import Conversor from "./components/Conversor"

function App() {
  return (
    <div className="App">

      <h1>Conversor de moedas</h1>

      <div className="linha">
        <Conversor moedaDe="USD" moedaPara="BRL"></Conversor>
        <Conversor moedaDe="BRL" moedaPara="USD"></Conversor>
      </div>

      <div className="linha">
        <Conversor moedaDe="CAD" moedaPara="BRL"></Conversor>
        <Conversor moedaDe="BRL" moedaPara="CAD"></Conversor>
      </div>

      <div className="linha">
        <Conversor moedaDe="EUR" moedaPara="BRL"></Conversor>
        <Conversor moedaDe="BRL" moedaPara="EUR"></Conversor>
      </div>

    </div>
  );
}

export default App;
