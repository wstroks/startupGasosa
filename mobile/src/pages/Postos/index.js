import React, { useEffect, useState } from 'react';
import { Feather } from '@expo/vector-icons';
import { useNavigation } from '@react-navigation/native';
import { View, FlatList, Image, Text, TextInput, TouchableOpacity } from 'react-native';

import shell from '../../assets/img/shell.png';
import menorPreco from '../../assets/img/menor-preco.png';
import petrobras from '../../assets/img/petrobras.png';
import ipiranga from '../../assets/img/ipiranga.jpg';
import outros from '../../assets/img/outros.jpg';

import api from '../../services/api';

import styles from './styles';

export default function Postos () {
    const [postos, setPostos] = useState([]);
    const [query, setQuery] = useState('');
    const navigation = useNavigation();

    function navigateToPosto (posto) {
        navigation.navigate('Posto', posto);
    }

    async function getPostos () {
        try {
            const response = await api.get('postos');

            setPostos(response.data);

            console.log(response.data);
        } catch (error) {
            alert('Erro ao obter os dados');
        }
    }

    useEffect(() => {
        getPostos();
    }, []);

    const renderItem = ({ item }) => (
        (query !== '' ? item.nome.indexOf(query) !== -1 : item.nome)
        &&
        <View style={styles.postosContainer}>
            <View style={styles.header}>
                <Image
                    style={styles.bandeira}
                    source={
                        (item.bandeira === "shell" ? shell :
                            (item.bandeira === "menor") ? menorPreco :
                                (item.bandeira === "petrobras") ? petrobras :
                                    (item.bandeira === "ipiranga") ? ipiranga : outros)
                    }
                />
                <Text style={styles.nome}>{item.nome}</Text>
            </View>

            <View style={styles.body}>
                <Text style={styles.endereco}>
                    <Feather name="map-pin" size={16} />
                    {item.endereco}
                </Text>

                <TouchableOpacity
                    style={styles.acessoButton}
                    onPress={() => navigateToPosto(item)}
                >
                    <Text style={styles.textButton}>Ver posto</Text>
                </TouchableOpacity>
            </View>
        </View>
    );

    return (
        <View style={styles.container}>
            <View style={styles.busca}>
                <TextInput
                    style={styles.input}
                    value={query}
                    onChangeText={value => setQuery(value)}
                />

                <Feather name="search" size={15} color={"#b7c0ee"} style={styles.svg} />
            </View>

            <FlatList
                contentContainerStyle={styles.list}
                data={postos}
                keyExtractor={item => item.id}
                renderItem={renderItem}
            // onEndReachedThreshold={0.3}
            />
        </View>
    );
}