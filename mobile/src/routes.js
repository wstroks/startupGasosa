import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

const AppStack = createStackNavigator();

import Home from './pages/Home';
import Postos from './pages/Postos';
import Posto from './pages/Posto';

export default function Routes () {
    return (
        <NavigationContainer>

            <AppStack.Navigator screenOptions={{ headerShown: false }}>
                <AppStack.Screen name="Home" component={Home} />
                <AppStack.Screen name="Postos" component={Postos} />
                <AppStack.Screen name="Posto" component={Posto} />
            </AppStack.Navigator>

        </NavigationContainer>
    );
}