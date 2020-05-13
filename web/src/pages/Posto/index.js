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
        posto: {
            id: 1,
            nome: "POSTO SENADOR",
            endereco: "RUA SENADOR QUINTINO 1125 OLHOS D ÁGUA 44003541, FEIRA DE SANTANA",
            contato: "7532433792",
            status: "há 6 hora(s), 9 minuto(s) e 29 segundo(s)",
            cidade: "Feira de Santana",
            latitude: null,
            longitude: null,
            url: null,
            bandeira: null,
            created_at: "2020-05-13 19:27:20",
            updated_at: "2020-05-13 19:27:20",
            combustiveis: [
                {
                    id: 1,
                    posto_id: 1,
                    tipo: "GASOLINA C COMUM (B:3)",
                    valor: "R$ 3,49",
                    created_at: "2020-05-13 19:27:20",
                    updated_at: "2020-05-13 19:27:20"
                },
                {
                    id: 149,
                    posto_id: 1,
                    tipo: "ETANOL HIDRATADO COMUM (B:10)",
                    valor: "R$ 2,74",
                    created_at: "2020-05-13 19:27:39",
                    updated_at: "2020-05-13 19:27:39"
                },
                {
                    id: 208,
                    posto_id: 1,
                    tipo: "OLEO DIESEL B S10 COMUM (B:6)",
                    valor: "R$ 2,56",
                    created_at: "2020-05-13 19:27:52",
                    updated_at: "2020-05-13 19:27:52"
                }
            ]
        },
        distancia: 0,
    }

    async componentDidMount () {
        const { id } = this.props.match.params;

        const response = await api.get(`/postos/${id}`);

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