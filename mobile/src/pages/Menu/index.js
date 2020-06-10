import React from 'react';
import { View, Text } from 'react-native';
import { RectButton } from 'react-native-gesture-handler';
import { createStackNavigator } from '@react-navigation/stack';

import { Feather } from '@expo/vector-icons';

import AlcoolGasolina from '../../components/AlcoolGasolina';
import MediaPorKm from '../../components/MediaPorKm';
import QuantoIreiGastar from '../../components/QuantoIreiGastar';
import Sobre from '../../components/Sobre';
import Sugestoes from '../../components/Sugestoes';

import styles from './styles';

function OpcoesScreen ({ navigation }) {
    return (
        <View style={styles.menuContainer}>
            <RectButton
                style={styles.itemButton}
                onPress={() => navigation.navigate('ModalAlcoolGasolina')}
            >
                <Text style={styles.textButton}>Álcool x Gasolina</Text>
            </RectButton>

            <RectButton
                style={styles.itemButton}
                onPress={() => navigation.navigate('ModalMediaPorKm')}
            >
                <Text style={styles.textButton}>Média por Km percorrido</Text>
            </RectButton>

            <RectButton
                style={styles.itemButton}
                onPress={() => navigation.navigate('ModalQuantoIreiGastar')}
            >
                <Text style={styles.textButton}>Quanto irei gastar?</Text>
            </RectButton>

            <RectButton
                style={styles.itemButton}
                onPress={() => navigation.navigate('ModalSobre')}
            >
                <Text style={styles.textButton}>Sobre o aplicativo</Text>
            </RectButton>

            <RectButton
                style={styles.itemButton}
                onPress={() => navigation.navigate('ModalSugestoes')}
            >
                <Text style={styles.textButton}>Sugestões, Bugs e Comentários</Text>
            </RectButton>
        </View>
    );
}

function ModalAlcoolGasolinaScreen ({ navigation }) {
    return (
        <View
            style={{
                flex: 1,
                justifyContent: "center",
                backgroundColor: "transparent",
                position: "relative",
            }}
        >
            <Feather
                style={{
                    backgroundColor: "transparent",
                    position: "absolute",
                    top: 15,
                    left: 10,
                    zIndex: 10,
                }}
                name="arrow-left"
                size={24}
                color={"#b7c0ee"}
                onPress={() => navigation.goBack()}
            />
            <AlcoolGasolina />
        </View>
    );
}

function ModalMediaPorKmScreen ({ navigation }) {
    return (
        <View
            style={{
                flex: 1,
                justifyContent: "center",
                backgroundColor: "transparent",
                position: "relative",
            }}
        >
            <Feather
                style={{
                    backgroundColor: "transparent",
                    position: "absolute",
                    top: 15,
                    left: 10,
                    zIndex: 10,
                }}
                name="arrow-left"
                size={24}
                color={"#b7c0ee"}
                onPress={() => navigation.goBack()}
            />
            <MediaPorKm />
        </View>
    );
}

function ModalQuantoIreiGastarScreen ({ navigation }) {
    return (
        <View
            style={{
                flex: 1,
                justifyContent: "center",
                backgroundColor: "transparent",
                position: "relative",
            }}
        >
            <Feather
                style={{
                    backgroundColor: "transparent",
                    position: "absolute",
                    top: 15,
                    left: 10,
                    zIndex: 10,
                }}
                name="arrow-left"
                size={24}
                color={"#b7c0ee"}
                onPress={() => navigation.goBack()}
            />
            <QuantoIreiGastar />
        </View>
    );
}

function ModalSobreScreen ({ navigation }) {
    return (
        <View
            style={{
                flex: 1,
                justifyContent: "center",
                backgroundColor: "transparent",
                position: "relative",
            }}
        >
            <Feather
                style={{
                    backgroundColor: "transparent",
                    position: "absolute",
                    top: 15,
                    left: 10,
                    zIndex: 10,
                }}
                name="arrow-left"
                size={24}
                color={"#b7c0ee"}
                onPress={() => navigation.goBack()}
            />
            <Sobre />
        </View>
    );
}

function ModalSugestoesScreen ({ navigation }) {
    return (
        <View
            style={{
                flex: 1,
                justifyContent: "center",
                backgroundColor: "transparent",
                position: "relative",
            }}
        >
            <Feather
                style={{
                    backgroundColor: "transparent",
                    position: "absolute",
                    top: 15,
                    left: 10,
                    zIndex: 10,
                }}
                name="arrow-left"
                size={24}
                color={"#b7c0ee"}
                onPress={() => navigation.goBack()}
            />
            <Sugestoes />
        </View>
    );
}

const OpcoesStack = createStackNavigator();
const MenuStack = createStackNavigator();

function OpcoesStackScreen () {
    return (
        <OpcoesStack.Navigator headerMode="none">
            <OpcoesStack.Screen name="Opcoes" component={OpcoesScreen} />
        </OpcoesStack.Navigator>
    );
}

const Menu = () => {
    return (
        <MenuStack.Navigator mode="modal" headerMode="none">
            <MenuStack.Screen name="Main" component={OpcoesStackScreen} />
            <MenuStack.Screen name="ModalAlcoolGasolina" component={ModalAlcoolGasolinaScreen} />
            <MenuStack.Screen name="ModalMediaPorKm" component={ModalMediaPorKmScreen} />
            <MenuStack.Screen name="ModalQuantoIreiGastar" component={ModalQuantoIreiGastarScreen} />
            <MenuStack.Screen name="ModalSobre" component={ModalSobreScreen} />
            <MenuStack.Screen name="ModalSugestoes" component={ModalSugestoesScreen} />
        </MenuStack.Navigator>
    );
}

export default Menu;