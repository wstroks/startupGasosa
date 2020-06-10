import React from 'react';
import { Feather } from '@expo/vector-icons';
import { useNavigation } from '@react-navigation/native';
import { Text, TouchableOpacity } from 'react-native';

import styles from './styles';

export default function SearchButton () {
    const navigation = useNavigation();

    function navigateToPostos () {
        navigation.navigate('Postos');
    }

    return (
        <TouchableOpacity
            activeOpacity={0.7}
            style={styles.searchButton}
            onPress={() => navigateToPostos()}
        >
            <Feather name='search' size={13} color='#330c2f' />
            <Text style={styles.textButton}>Postos</Text>
        </TouchableOpacity>
    );
}