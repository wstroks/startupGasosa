import React from 'react';

import Header from '../../components/Header';

import './styles.css';

export default function QuantoIreiGastar () {
    return (
        <div className="box">
            <Header />
            <form action={() => { }}>
                <input type="text" name="valorLitro" id="valorLitro" placeholder="Valor do litro do combustível" />
                <input type="text" name="kmPercorrido" id="kmPercorrido" placeholder="Distância do trajeto (em Km)" />
                <input type="text" name="kmPorLitro" id="kmPorLitro" placeholder="Quilometragem média por litro" />
                <button type="submit">Calcular</button>
            </form>

            <div className="resultado">
                <span>Resultado</span>
                <p>
                    Você irá gastar R$ 0,00
                </p>
            </div>
        </div>
    );
}