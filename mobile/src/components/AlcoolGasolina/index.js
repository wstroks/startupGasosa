import React, { useState } from 'react';
import { View, Text, TextInput } from 'react-native';
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
        <View style={styles.container}>
            <TextInput
                style={styles.input}
                value={valorLitroAlcool}
                placeholder="Valor do litro do álcool"
                placeholderTextColor="#b7c0ee"
                onChangeText={value => setValorLitroAlcool(value)}
            />

            <TextInput
                style={styles.input}
                value={valorLitroGasolina}
                placeholder="Valor do litro da gasolina"
                placeholderTextColor="#b7c0ee"
                onChangeText={value => setValorLitroGasolina(value)}
            />

            <RectButton
                style={styles.button}
                onPress={() => handleCalculate}
            >
                <Text style={styles.textButton}>Calcular</Text>
            </RectButton>

            <View style={styles.resultado}>
                <Text style={styles.span}>Resultado</Text>
                <Text style={styles.p}>Melhor abastecer com {combustivel}</Text>
            </View>
        </View>
    );
}