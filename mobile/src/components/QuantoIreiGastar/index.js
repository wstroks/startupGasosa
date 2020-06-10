import React, { useState } from 'react';
import { View, Text, TextInput } from 'react-native';
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
        <View style={styles.container}>
            <TextInput
                style={styles.input}
                value={valorLitro}
                placeholder="Valor do litro do combustível"
                placeholderTextColor="#b7c0ee"
                onChangeText={value => setValorLitro(value)}
            />

            <TextInput
                style={styles.input}
                value={kmPercorrido}
                placeholder="Distância do trajeto (em Km)"
                placeholderTextColor="#b7c0ee"
                onChangeText={value => setKmPercorrido(value)}
            />

            <TextInput
                style={styles.input}
                value={kmPorLitro}
                placeholder="Quilometragem média por litro"
                placeholderTextColor="#b7c0ee"
                onChangeText={value => setKmPorLitro(value)}
            />

            <RectButton
                style={styles.button}
                onPress={() => handleCalculate}
            >
                <Text style={styles.textButton}>Calcular</Text>
            </RectButton>

            <View style={styles.resultado}>
                <Text style={styles.span}>Resultado</Text>
                <Text style={styles.p}>Você irá gastar R${valor}</Text>
            </View>
        </View>
    );
}