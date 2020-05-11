import React from 'react';
import { isAuthenticated } from './auth';
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';

import Login from './pages/Login';
import Cadastro from './pages/Cadastro';
import AlcoolGasolina from './pages/AlcoolGasolina';
import QuantoIreiGastar from './pages/QuantoIreiGastar';
import MediaPorKm from './pages/MediaPorKm';
import ContribuirPreco from './pages/ContribuirPreco';
import Home from './pages/Home';

const PrivateRoute = ({ component: Component, ...rest }) => (
    <Route
        {...rest}
        render={props => (
            isAuthenticated() ? (
                <Component {...props} />
            ) : (
                    <Redirect to={{ pathname: '/', state: { from: props.location } }} />
                )
        )} />
)

const Routes = () => (
    <BrowserRouter>
        <Switch>
            <Route exact path="/" component={Login} />
            <Route path="/registro" component={Cadastro} />
            <PrivateRoute path="/home" component={Home} />
            <PrivateRoute path="/alcool-gasolina" component={AlcoolGasolina} />
            <PrivateRoute path="/quanto-irei-gastar" component={QuantoIreiGastar} />
            <PrivateRoute path="/media-por-km" component={MediaPorKm} />
            <PrivateRoute path="/contribuir-preco" component={ContribuirPreco} />
        </Switch>
    </BrowserRouter>
);

export default Routes;