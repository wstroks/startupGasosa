import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

import Tabs from './routesHome';

import Postos from '../pages/Postos';
import Posto from '../pages/Posto';

import SearchButton from '../components/SearchButton';
import Logo from '../components/Logo';

const AppStack = createStackNavigator();

export default function Routes () {
    return (
        <NavigationContainer>
            <AppStack.Navigator screenOptions={{
                title: 'Gasosa',
                headerTitleAlign: 'center',
                headerTitleStyle: {
                    color: '#7b287d',
                },
                headerStyle: {
                    backgroundColor: '#f9f9f9',
                },
                headerLeft: () => (
                    <Logo />
                ),
                headerRight: () => (
                    <SearchButton />
                ),
            }}>
                <AppStack.Screen name="Home" component={Tabs} />
                <AppStack.Screen name="Postos" component={Postos} />
                <AppStack.Screen name="Posto" component={Posto} />
            </AppStack.Navigator>
        </NavigationContainer>
    );
}