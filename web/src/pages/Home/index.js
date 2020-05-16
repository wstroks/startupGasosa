import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Card } from 'react-bootstrap';

import {
    FiCornerUpRight,
    FiMapPin,
    FiShare2,
} from "react-icons/fi";

import shell from '../../assets/img/shell.png';
import menorPreco from '../../assets/img/menor-preco.png';
import petrobras from '../../assets/img/petrobras.png';

import Header from '../../components/Header';

import './styles.css';

import api from '../../services/api';

export default function Home () {
    const [postos, setPostos] = useState([]);
    const [distancia, setDistancia] = useState('');

    async function getPostos () {
        try {
            const response = await api.get('postos');
            console.log(response.data);

            setPostos(response.data);
        } catch (error) {
            alert('Erro ao obter os dados');
        }
    }

    useEffect(() => {
        getPostos();
    }, []);

    function handleDistance () {
        setDistancia(0);
    }

    return (
        <div className="box-home">
            <Header />

            {postos.map(posto => (
                <Card key={posto.id}>
                    <Card.Header>
                        <img src={(posto.bandeira === "shell" ? shell : (posto.bandeira === "menor-preco") ? menorPreco : petrobras)} alt="" />
                        <h3>{posto.nome}</h3>
                    </Card.Header>
                    <Card.Body>
                        <h4><FiMapPin size={16} /> {posto.endereco}</h4>

                        <ul className="combustiveis">
                            {posto.combustiveis.map(combustivel => (
                                <li key={combustivel.id}><span>{combustivel.tipo}</span> <span>{combustivel.valor}</span></li>
                            ))}
                        </ul>

                        <div className="links">
                            <Link to={`/postos/${posto.id}`}>Acessar</Link>
                        </div>
                    </Card.Body>
                </Card>
            ))}
        </div>
    );
}