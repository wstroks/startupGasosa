import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import {
    FiPlusCircle,
    FiMapPin,
} from "react-icons/fi";

import shell from '../../assets/img/shell.png';
import menorPreco from '../../assets/img/menor-preco.png';
import petrobras from '../../assets/img/petrobras.png';

import './styles.css';

import api from '../../services/api';

export default class Posto extends Component {
    state = {
        posto: {},
        distancia: 0,
    }

    async componentDidMount () {
        const { id } = this.props.match.params;

        const response = await api.get(`postos/${id}`);

        this.setState({ posto: response.data });
    }

    render () {
        const { posto, distancia } = this.state;

        return (
            <div className="box-posto">
                <div className="posto" key={posto.id}>
                    <div className="linha">
                        <div className="bandeira">
                            <img src={(posto.bandeira === "shell" ? shell : (posto.bandeira === "menor-preco") ? menorPreco : petrobras)} alt="" />
                        </div>

                        <h3>{posto.nome}</h3>
                    </div>

                    <div className="endereco">
                        <span><FiMapPin size={16} /></span>
                        <span>{posto.endereco}</span>
                        <span>{distancia} Km</span>
                    </div>

                    <ul className="combustiveis">
                        {posto.combustiveis.map(combustivel => (
                            <li key={combustivel.id}><span>{combustivel.tipo}</span> <span>{combustivel.valor}</span></li>
                        ))}
                    </ul>
                </div>
            </div>
        );
    }
}