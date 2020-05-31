import React, { useEffect, useState } from 'react';
import { Feather } from '@expo/vector-icons';
import { useNavigation } from '@react-navigation/native';
import { View, FlatList, Image, Text, TouchableOpacity } from 'react-native';

import api from '../../services/api';
import styles from './styles';

export default function Home () {
    const navigation = useNavigation();

    function navigateToPosto () {
        navigation.navigate('Posto');
    }

    function navigateToPostos () {
        navigation.navigate('Postos');
    }

    return (
        <View style={styles.container}>
            <Text style={styles.pageName}>Home</Text>

            <View style={styles.lineButtons}>
                <TouchableOpacity
                    onPress={() => navigateToPosto()}
                >
                    <Text>Posto</Text>
                </TouchableOpacity>

                <TouchableOpacity
                    onPress={() => navigateToPostos()}
                >
                    <Text>Postos</Text>
                </TouchableOpacity>
            </View>
        </View>
    );
}