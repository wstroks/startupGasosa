import React from 'react';

import './styles.css';
import gasosa from '../../assets/img/gasosa.jpg';

export default function Login () {
    return (
        <div className="box">
            <div className="logo">
                <img src={gasosa} alt="" />
            </div>
            <form action={() => { }}>
                <input type="email" name="email" id="email" placeholder="Email" required />
                <input type="password" name="password" id="password" placeholder="Senha" required />
                <button type="submit">Entrar</button>
                <a href={() => { }}>Cadastre-se</a>
            </form>
        </div>
    );
}