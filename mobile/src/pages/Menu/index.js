import React from 'react';
import { View, Text } from 'react-native';
import { RectButton } from 'react-native-gesture-handler';

import AlcoolGasolina from '../../components/AlcoolGasolina';
// import MediaPorKm from '../../components/MediaPorKm';
// import QuantoIreiGastar from '../../components/QuantoIreiGastar';
// import Sobre from '../../components/Sobre';
// import Sugestoes from '../../components/Sugestoes';

import styles from './styles';

const Menu = () => {
    return (
        <View style={styles.menuContainer}>
            <RectButton
                style={styles.itemButton}
                onPress={() => { }}
            >
                <Text style={styles.textButton}>Álcool x Gasolina</Text>
            </RectButton>

            <RectButton
                style={styles.itemButton}
                onPress={() => { }}
            >
                <Text style={styles.textButton}>Média por Km percorrido</Text>
            </RectButton>

            <RectButton
                style={styles.itemButton}
                onPress={() => { }}
            >
                <Text style={styles.textButton}>Quanto irei gastar?</Text>
            </RectButton>

            <RectButton
                style={styles.itemButton}
                onPress={() => { }}
            >
                <Text style={styles.textButton}>Sobre o aplicativo</Text>
            </RectButton>

            <RectButton
                style={styles.itemButton}
                onPress={() => { }}
            >
                <Text style={styles.textButton}>Sugestões, Bugs e Comentários</Text>
            </RectButton>
        </View>
    );
}

export default Menu;