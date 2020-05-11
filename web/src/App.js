import React from 'react';

import Login from './pages/Login';
import Cadastro from './pages/Cadastro';
import AlcoolGasolina from './pages/AlcoolGasolina';
import QuantoIreiGastar from './pages/QuantoIreiGastar';
import MediaPorKm from './pages/MediaPorKm';
import ContribuirPreco from './pages/ContribuirPreco';

function App () {
  return (
    <>
      <Login />
      <Cadastro />
      <QuantoIreiGastar />
      <AlcoolGasolina />
      <MediaPorKm />
      <ContribuirPreco />
    </>
  );
}

export default App;
