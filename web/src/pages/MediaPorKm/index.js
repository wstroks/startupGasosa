import React from 'react';

import Header from '../../components/Header';

import './styles.css';

export default function MediaPorKm () {
    return (
        <div className="box">
            <Header />
            <form action={() => { }}>
                <input type="text" name="kmPercorridos" id="kmPercorridos" placeholder="Percorreu quantos Km?" required />
                <input type="text" name="qtdLitros" id="qtdLitros" placeholder="Abasteceu quantos litros?" required />
                <button type="submit">Calcular</button>
            </form>

            <div className="resultado">
                <span>Resultado</span>
                <p>
                    Foram percorridos x Km/litro
                </p>
            </div>
        </div>
    );
}