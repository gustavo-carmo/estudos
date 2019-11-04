import React from 'react';

//import Contador from './Contador';
import PlacarContainer from './PlacarContainer';

const dados = {
    partida: {
        estadio: "Maracanã/RJ",
        data: "01/08/2019",
        hora: "20h"
    },
    casa: {
        nome: "Vasco"
    },
    visitante: {
        nome: "Flamengo"
    }
};

//Para se renderizar algo no html é necessário retornar uma classe que retorne um html no metodo render
//export default
export default class App extends React.Component {
    render() {
        //return <h1> Olá React </h1> - Quando se vai retornar apenas uma linha de html pode retornar assim
        /*return (
            <div>
                <Contador />
            </div>
        );*/
        return <PlacarContainer {...dados} clima={1} />;
    }
} 