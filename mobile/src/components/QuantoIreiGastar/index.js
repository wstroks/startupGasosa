import React, { useState } from 'react';
import { View, TextInput } from 'react-native';
import { RectButton } from 'react-native-gesture-handler';

import styles from './styles';

export default function QuantoIreiGastar () {
    const [valorLitro, setValorLitro] = useState('');
    const [kmPercorrido, setKmPercorrido] = useState('');
    const [kmPorLitro, setKmPorLitro] = useState('');

    const [valor, setValor] = useState('');

    function handleCalculate () {
        setValor(((valorLitro * kmPercorrido) / kmPorLitro).toFixed(2));
    }

    return (
        <View>
            <TextInput
                value={valorLitro}
                placeholder="Valor do litro do combustível"
                onChangeText={value => setValorLitro(value)}
            />

            <TextInput
                // style={styles.input}
                value={kmPercorrido}
                placeholder="Distância do trajeto (em Km)"
                onChangeText={value => setKmPercorrido(value)}
            />

            <TextInput
                // style={styles.input}
                value={kmPorLitro}
                placeholder="Quilometragem média por litro"
                onChangeText={value => setKmPorLitro(value)}
            />

            <RectButton
                onPress={() => handleCalculate}
            >
                <Text>Calcular</Text>
            </RectButton>

            <View>
                <Text>Resultado</Text>
                <Text>Você irá gastar R${valor}</Text>
            </View>
        </View>
    );
}