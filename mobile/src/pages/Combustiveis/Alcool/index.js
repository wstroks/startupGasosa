import React, { useEffect, useState } from 'react';
import { Feather } from '@expo/vector-icons';
import { useNavigation } from '@react-navigation/native';
import { View, FlatList, Image, Text } from 'react-native';

import shell from '../../../assets/img/shell.png';
import menorPreco from '../../../assets/img/menor-preco.png';
import petrobras from '../../../assets/img/petrobras.png';
import ipiranga from '../../../assets/img/ipiranga.jpg';
import outros from '../../../assets/img/outros.jpg';

import api from '../../../services/api';

import styles from './styles';

export default function Alcool () {
    const navigation = useNavigation();

    function navigateToPosto () {
        navigation.navigate('Posto');
    }

    function navigateToPostos () {
        navigation.navigate('Postos');
    }

    const [combustiveis, setCombustiveis] = useState([]);

    let distancias = [];

    const [latitude, setLatitude] = useState('');
    const [longitude, setLongitude] = useState('');

    useEffect(() => {
        navigator.geolocation.getCurrentPosition(
            (position) => {
                const { latitude, longitude } = position.coords;

                setLatitude(latitude);
                setLongitude(longitude);
            },
            (err) => {
                console.log(err);
            },
            {
                timeout: 30000,
            }
        )
    }, []);

    async function getCombustiveis () {
        try {
            const response = await api.get('combustiveis');

            setCombustiveis(response.data);

            console.log(response.data);
        } catch (error) {
            alert('Erro ao obter os dados');
        }
    }

    useEffect(() => {
        getCombustiveis();
    }, []);

    function handleDistance (lat1, lon1, lat2, lon2) {
        let R = 6371;
        let dLat = (lat2 - lat1) * (Math.PI / 180);
        let dLon = (lon2 - lon1) * (Math.PI / 180);

        let a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1 * (Math.PI / 180)) * Math.cos(lat2 * (Math.PI / 180)) * Math.sin(dLon / 2) * Math.sin(dLon / 2)
        let c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        let d = R * c

        return d;
    }

    const renderItem = ({ item }) => (item.tipo.indexOf("ETANOL") !== -1) &&
    (
        <View style={styles.combustivelContainer}>
            <View style={styles.header}>
                <Image
                    style={styles.bandeira}
                    source={
                        (item.postos.bandeira === "shell" ? shell :
                            (item.postos.bandeira === "menor") ? menorPreco :
                                (item.postos.bandeira === "petrobras") ? petrobras :
                                    (item.postos.bandeira === "ipiranga") ? ipiranga : outros)
                    }
                />

                <Text style={styles.nome}>{item.postos.nome}</Text>

                <Feather name='share-2' size={20} color='#f9f9f9' />
            </View>

            <View style={styles.body}>
                <View>
                    <Feather name="map-pin" size={20} color="#7B287D" />
                    <Text>{item.postos.endereco}</Text>
                </View>

                <View>
                    <Text>
                        {item.valor}
                    </Text>

                    <Text>
                        {item.postos.latitude !== null ? `a ${handleDistance(latitude, longitude, item.postos.latitude, item.postos.longitude).toFixed(2)} Km` : ''}
                    </Text>

                    <View>
                        <Feather name="corner-up-right" size={20} color="#f9f9f9" />
                        <Text>Ver no mapa</Text>
                    </View>
                </View>

                <View>
                    <Text>
                        Atualizado em: {item.updated_at.substr(0, 10).split('-').reverse().join('/')}
                    </Text>
                </View>
            </View>
        </View>
    );

    return (
        <View style={styles.container}>
            <FlatList
                contentContainerStyle={styles.list}
                data={combustiveis}
                keyExtractor={item => item.id}
                renderItem={renderItem}
                // onEndReachedThreshold={0.3}
            />
        </View>
    );
}