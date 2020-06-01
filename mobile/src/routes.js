import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

const AppStack = createStackNavigator();

import Home from './pages/Home';
import Postos from './pages/Postos';
import Posto from './pages/Posto';

import SearchButton from './components/SearchButton';
import Logo from './components/Logo';

export default function Routes () {
    return (
        <NavigationContainer>

            <AppStack.Navigator screenOptions={{
                title: '',
                
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
                <AppStack.Screen name="Home" component={Home} />
                <AppStack.Screen name="Postos" component={Postos} />
                <AppStack.Screen name="Posto" component={Posto} />
            </AppStack.Navigator>

        </NavigationContainer>
    );
}