import React, { useState } from 'react';
import { View, Text, TextInput } from 'react-native';
import { RectButton } from 'react-native-gesture-handler';

import styles from './styles';

import api from '../../services/api';

export default function Sugestoes () {
    const [sugestoes, setSugestoes] = useState('');

    async function handleSugestoes () {
        try {
            await api.post('/comentarios', {
                descricao: sugestoes,
            });
        } catch (error) {
            alert("Erro ao enviar as sugestões");
        }
        setSugestoes('');
    }

    return (
        <View style={styles.container}>
            <Text style={styles.span}>Seu feedback é fundamental para a melhoria do "Gasosa!"</Text>

            <TextInput
                style={styles.input}
                multiline={true}
                numberOfLines={10}
                value={sugestoes}
                onChangeText={value => setSugestoes(value)}
            />

            <RectButton
                style={styles.button}
                onPress={() => handleSugestoes}
            >
                <Text style={styles.textButton}>Enviar</Text>
            </RectButton>
        </View>
    )
};