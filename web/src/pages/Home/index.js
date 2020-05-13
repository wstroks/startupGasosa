import React, { useState, useEffect } from 'react';

import {
    FiPlus,
    FiMapPin,
} from "react-icons/fi";

import shell from '../../assets/img/shell.png';
import menorPreco from '../../assets/img/menor-preco.png';
import petrobras from '../../assets/img/petrobras.png';

import './styles.css';

export default function Home () {
    const [postos, setPostos] = useState([]);
    const [distancia, setDistancia] = useState();

    const posto1 = {
        id: 1,
        nome: "Posto Trevo",
        endereco: "Rua do Posto",
        gasolina: 4,
        gasolinaAd: 4.5,
        diesel: 3.8,
        dieselAd: 4.2,
        alcool: 3.5,
        gnv: 3,
        bandeira: "shell",
        latitude: "12.1913696",
        longitude: "-68.9759875",
    };

    const posto2 = {
        id: 2,
        nome: "Posto Ipiranga",
        endereco: "Rua do Posto Ipiranga",
        gasolina: 4,
        gasolinaAd: 4.5,
        diesel: 3.8,
        dieselAd: 4.2,
        alcool: 3.5,
        gnv: 3,
        bandeira: "menor-preco",
        latitude: "22.1913696",
        longitude: "-68.9759875",
    };

    const posto3 = {
        id: 3,
        nome: "Posto Eucalipto",
        endereco: "Rua do Posto Eucalipto",
        gasolina: 4,
        gasolinaAd: 4.5,
        diesel: 3.8,
        dieselAd: 4.2,
        alcool: 3.5,
        gnv: 3,
        bandeira: "petrobras",
        latitude: "32.1913696",
        longitude: "-68.9759875",
    };

    useEffect(() => {
        setPostos([posto1, posto2, posto3, posto1, posto2, posto3]);
        handleDistance();
    }, [posto1, posto2, posto3]);

    function handleDistance () {
        setDistancia(0);
    }

    return (
        <div className="box-home">
            <div className="postos">
                {postos.map(posto => (
                    <div className="posto" key={posto.id}>
                        <div className="linha">
                            <div className="bandeira">
                                <img src={(posto.bandeira === "shell" ? shell : (posto.bandeira === "menor-preco") ? menorPreco : petrobras)} alt="" />
                            </div>

                            <h3>{posto.nome}</h3>

                            <span><FiPlus size={16} /></span>
                        </div>

                        <div className="endereco">
                            <span><FiMapPin size={16} /></span>
                            <span>{posto.endereco}</span>
                            <span>a {distancia} Km</span>
                        </div>

                        <ul className="combustiveis">
                            <li>Gasolina: <span>R$ {posto.gasolina.toFixed(3)}</span></li>
                            <li>Gasolina Aditivada: <span>R$ {posto.gasolinaAd.toFixed(3)}</span></li>
                            <li>Diesel: <span>R$ {posto.diesel.toFixed(3)}</span></li>
                            <li>Diesel Aditivado: <span>R$ {posto.dieselAd.toFixed(3)}</span></li>
                            <li>Álcool: <span>R$ {posto.alcool.toFixed(3)}</span></li>
                            <li>Gás (GNV): <span>R$ {posto.gnv.toFixed(3)}</span></li>
                        </ul>
                    </div>
                ))}
            </div>
        </div>
    );
}