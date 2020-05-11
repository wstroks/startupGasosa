import React from 'react';

import './styles.css';
import gasosa from '../../assets/img/gasosa.jpg';

export default function Cadastro () {
    return (
        <div className="box">
            <div className="logoCadastro">
                <img src={gasosa} alt="" />
            </div>
            <form action={() => { }}>
                <input type="text" name="name" id="name" placeholder="Nome" required />
                <input type="email" name="email" id="email" placeholder="Email" required />
                <input type="password" name="password" id="password" placeholder="Senha" required />
                <input type="password" name="confirmPassword" id="confirmPassword" placeholder="Confirme sua Senha" required />
                <button type="submit">Cadastrar</button>
            </form>
        </div>
    );
}