import React, { useEffect, useState } from 'react';
import { Feather, FontAwesome5 } from '@expo/vector-icons';
import { useNavigation, useRoute } from '@react-navigation/native';
import { View, Image, Text, TouchableOpacity } from 'react-native';

import shell from '../../assets/img/shell.png';
import menorPreco from '../../assets/img/menor-preco.png';
import petrobras from '../../assets/img/petrobras.png';
import ipiranga from '../../assets/img/ipiranga.jpg';
import outros from '../../assets/img/outros.jpg';

import styles from './styles';

export default function Posto () {
    const navigation = useNavigation();

    function handleNavigateBack () {
        navigation.goBack();
    };

    const route = useRoute();

    const posto = route.params;

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

    function handleDistance (lat1, lon1, lat2, lon2) {
        let R = 6371;
        let dLat = (lat2 - lat1) * (Math.PI / 180);
        let dLon = (lon2 - lon1) * (Math.PI / 180);

        let a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1 * (Math.PI / 180)) * Math.cos(lat2 * (Math.PI / 180)) * Math.sin(dLon / 2) * Math.sin(dLon / 2)
        let c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        let d = R * c

        return d;
    }

    return (
        <View style={styles.container}>
            <TouchableOpacity
                style={styles.back}
                onPress={() => handleNavigateBack}
            >
                <Feather
                    name="arrow-left"
                    size={20}
                    color={"#b7c0ee"}
                />
            </TouchableOpacity>

            <View style={styles.postoContainer}>
                <View style={styles.header}>
                    <Image
                        style={styles.bandeira}
                        source={
                            (posto.bandeira === "shell" ? shell :
                                (posto.bandeira === "menor") ? menorPreco :
                                    (posto.bandeira === "petrobras") ? petrobras :
                                        (posto.bandeira === "ipiranga") ? ipiranga : outros)
                        }
                    />
                    <Text style={styles.nome}>{posto.nome}</Text>
                </View>

                <View style={styles.body}>
                    <View style={styles.viewEndereco}>
                        <Feather name="map-pin" size={16} color={"#7b287d"} />
                        <Text style={styles.endereco}>{posto.endereco}</Text>
                    </View>

                    <Text style={styles.distancia}>{posto.latitude !== null ? `Situado a ${handleDistance(latitude, longitude, posto.latitude, posto.longitude).toFixed(2)} Km do seu local` : ''}</Text>

                    <TouchableOpacity
                        style={styles.verNoMapa}
                        onPress={() => { }}
                    >
                        <Feather name="corner-up-right" size={14} color={"#f9f9f9"} />
                        <Text style={styles.textButton}>Ver no mapa</Text>
                    </TouchableOpacity>

                    <View>
                        {posto.combustiveis.map(combustivel => (
                            <View style={styles.combustivel} key={combustivel.id}>
                                <Text style={styles.nomeCombustivel}>{combustivel.tipo}</Text>
                                <Text style={styles.valorCombustivel}>{combustivel.valor}</Text>
                            </View>
                        ))}
                    </View>

                    <Text style={styles.compartilhar}>Compartilhar via</Text>

                    <View style={styles.socialMedia}>
                        <TouchableOpacity
                            style={styles.media}
                            onPress={() => { }}
                        >
                            <FontAwesome5 name="whatsapp" size={16} color={"#f9f9f9"} />
                        </TouchableOpacity>

                        <TouchableOpacity
                            style={styles.media}
                            onPress={() => { }}
                        >
                            <FontAwesome5 name="facebook-f" size={16} color={"#f9f9f9"} />
                        </TouchableOpacity>

                        <TouchableOpacity
                            style={styles.media}
                            onPress={() => { }}
                        >
                            <FontAwesome5 name="telegram-plane" size={16} color={"#f9f9f9"} />
                        </TouchableOpacity>

                        <TouchableOpacity
                            style={styles.media}
                            onPress={() => { }}
                        >
                            <FontAwesome5 name="twitter" size={16} color={"#f9f9f9"} />
                        </TouchableOpacity>
                    </View>
                </View>
            </View>
        </View>
    );
}