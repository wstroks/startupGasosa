import React, { useEffect, useState } from 'react';
import { Feather } from '@expo/vector-icons';
import { useNavigation } from '@react-navigation/native';
import { View, FlatList, Image, Text, TouchableOpacity } from 'react-native';

import api from '../../services/api';
import styles from './styles';

export default function Postos () {
    const navigation = useNavigation();

    function navigateToHome () {
        navigation.navigate('Home');
    }

    function navigateToPosto () {
        navigation.navigate('Posto');
    }

    return (
        <View style={styles.container}>
            <Text style={styles.pageName}>Postos</Text>

            <View style={styles.lineButtons}>
                <TouchableOpacity
                    onPress={() => navigateToHome()}
                >
                    <Text>Home</Text>
                </TouchableOpacity>

                <TouchableOpacity
                    onPress={() => navigateToPosto()}
                >
                    <Text>Posto</Text>
                </TouchableOpacity>
            </View>
        </View>
    );
}