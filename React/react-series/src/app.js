import React from 'react';
import ReactDOM from 'react-dom';

import { Button } from 'element-react';

/*import 'element-theme-default';*/

import App from './components/App';

//document.getElementById("Id do elemento") - encontra o elemento no index.html que sera substituido pelo conteúdo de App
ReactDOM.render(
    <App />,
    document.getElementById("app")
);

/*
ReactDOM.render(
    <Button type="primary">Hello</Button>,
    document.getElementById("app-test")
);*/