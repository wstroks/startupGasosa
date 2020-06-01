import React from 'react';
import { useNavigation } from '@react-navigation/native';
import { Image, TouchableOpacity } from 'react-native';

import logo from '../../assets/img/logo.png';

import styles from './styles';

export default function Logo () {
    const navigation = useNavigation();

    function navigateToHome () {
        navigation.navigate('Home');
    }

    return (
        <TouchableOpacity
            onPress={() => navigateToHome()}
        >
            <Image
                style={styles.logo}
                source={logo}
            />
        </TouchableOpacity>
    );
}