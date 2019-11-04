import React from 'react';

export default class Contador extends React.Component {

    constructor() {
        super ();

        this.state = {
            contador: 1,
            alterator: 0
        }
    }

    incrementar() {
        this.setState({
            contador: this.state.contador + 1
        });
    }

    decrementar() {
        this.setState({
            contador: this.state.contador - 1
        });
    }

    incrementar2() {
        this.setState({
            alterator: this.state.contador + 1
        });
    }

    decrementar2() {
        this.setState({
            alterator: this.state.contador - 1
        });
    }

    render() {
        return (
            <div>
                <h2>{this.state.alterator}</h2>
                <button onClick={this.decrementar2.bind(this)}>-</button>
                <button onClick={this.incrementar2.bind(this)}>+</button>
                <h1>{this.state.contador}</h1>
                <button onClick={this.decrementar.bind(this)}>-</button>
                <button onClick={this.incrementar.bind(this)}>+</button>
            </div>
        );
    }
}