import React from 'react';

import './styles.css';

export default function Sugestoes () {
    return (
        <div className="box">
            <h4 className="titulo">
                Seu feedback é fundamental para a melhoria do "Gasosa!"
            </h4>

            <form action="">
                <textarea
                    name=""
                    id=""
                    rows="15"
                >
                </textarea>
                <button type="submit">Enviar</button>
            </form>
        </div>
    )
};