import React, { useState } from 'react';
import { View, Text, TextInput } from 'react-native';
import { RectButton } from 'react-native-gesture-handler';

import styles from './styles';

export default function MediaPorKm () {
    const [kmPercorridos, setKmPercorridos] = useState('');
    const [qtdLitros, setQtdLitros] = useState('');

    const [kmPorLitro, setKmPorLitro] = useState('');

    function handleCalculate () {
        setKmPorLitro((kmPercorridos / qtdLitros).toFixed(2));
    }

    return (
        <View style={styles.container}>
            <TextInput
                style={styles.input}
                value={kmPercorridos}
                placeholder="Percorreu quantos Km?"
                placeholderTextColor="#b7c0ee"
                onChangeText={value => setKmPercorridos(value)}
            />

            <TextInput
                style={styles.input}
                value={qtdLitros}
                placeholder="Abasteceu quantos litros?"
                placeholderTextColor="#b7c0ee"
                onChangeText={value => setQtdLitros(value)}
            />

            <RectButton
                style={styles.button}
                onPress={() => handleCalculate}
            >
                <Text style={styles.textButton}>Calcular</Text>
            </RectButton>

            <View style={styles.resultado}>
                <Text style={styles.span}>Resultado</Text>
                <Text style={styles.p}>Foram percorridos {kmPorLitro} Km/litro</Text>
            </View>
        </View>
    );
}