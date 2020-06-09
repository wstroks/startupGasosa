import React, { useState } from 'react';
import { View, TextInput } from 'react-native';
import { RectButton } from 'react-native-gesture-handler';

import styles from './styles';

export default function AlcoolGasolina () {
    const [valorLitroAlcool, setValorLitroAlcool] = useState('');
    const [valorLitroGasolina, setValorLitroGasolina] = useState('');

    const [combustivel, setCombustivel] = useState('');

    function handleCalculate () {
        (parseFloat(valorLitroAlcool) / parseFloat(valorLitroGasolina) < .7 ? setCombustivel('Álcool') : setCombustivel('Gasolina'));
    }

    return (
        <View>
            <TextInput
                // style={styles.input}
                value={valorLitroAlcool}
                placeholder="Valor do litro do álcool"
                onChangeText={value => setValorLitroAlcool(value)}
            />

            <TextInput
                // style={styles.input}
                value={valorLitroGasolina}
                placeholder="Valor do litro da gasolina"
                onChangeText={value => setValorLitroGasolina(value)}
            />

            <RectButton
                onPress={() => handleCalculate}
            >
                <Text>Calcular</Text>
            </RectButton>

            <View>
                <Text>Resultado</Text>
                <Text>Melhor abastecer com {combustivel}</Text>
            </View>
        </View>
    );
}