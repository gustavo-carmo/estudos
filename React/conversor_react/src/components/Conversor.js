import React, { Component } from "react";

import './Conversor.css'

export default class Conversor extends Component {

    constructor(props) {
        super(props);

        this.state = {
            moedaDeValor: "",
            moedaParaValor: 0
        }

        this.converter = this.converter.bind(this);
    }

    converter() {

        let dePara = `${this.props.moedaDe}_${this.props.moedaPara}`

        let url = `https://free.currconv.com/api/v7/convert?q=${dePara}&compact=ultra&apiKey=fc27556c7f61d45e4a1b`
        
        fetch(url).then(response => {

            console.log("Resposta -> ", response);
            return response.json();
        }).then(json => {
            console.log("Resposta como JSON -> ", json);
            console.log("DEPARA -> ", json[dePara]);
            let cotacao = json[dePara];

            let moedaParaValorPosConversao = (parseFloat(this.state.moedaDeValor) * cotacao).toFixed(2);
            this.setState({moedaParaValor: moedaParaValorPosConversao});
        });
    }

    render() {
        return (
            <div className="conversor">
                <h2>{this.props.moedaDe} para {this.props.moedaPara}</h2>
                <input type="text" onChange={(event) => { this.setState({moedaDeValor:event.target.value})}}></input>
                <input type="button" value="Converter" onClick={this.converter}></input>
                <h2>{this.state.moedaParaValor}</h2>
            </div>
        )
    }
}