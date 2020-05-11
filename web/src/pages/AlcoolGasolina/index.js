import React from 'react';

import Header from '../../components/Header';

import './styles.css';

export default function AlcoolGasolina () {
    return (
        <div className="box">
            <Header />
            <form action={() => { }}>
                <input type="text" name="valorLitroAlcool" id="valorLitroAlcool" placeholder="Valor do litro do álcool" required />
                <input type="text" name="valorLitroGasolina" id="valorLitroGasolina" placeholder="Valor do litro da gasolina" required />
                <button type="submit">Calcular</button>
            </form>

            <div className="resultado">
                <span>Resultado</span>
                <p>
                    Melhor abastecer com:
                </p>
            </div>
        </div>
    );
}