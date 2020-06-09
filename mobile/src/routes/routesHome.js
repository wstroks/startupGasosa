import React from 'react';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

const Tab = createBottomTabNavigator();

import Gasolina from '../pages/Combustiveis/Gasolina';
import Alcool from '../pages/Combustiveis/Alcool';
import Diesel from '../pages/Combustiveis/Diesel';
import Gnv from '../pages/Combustiveis/Gnv';
import Menu from '../pages/Menu';

export default function Tabs () {
    return (
        <Tab.Navigator
            tabBarOptions={{
                style: {
                    height: 50,
                },
                tabStyle: {
                    height: 50,
                    justifyContent: "center",
                    alignItems: "center",
                },
                activeTintColor: '#7b287d',
                inactiveTintColor: '#7b287d',
                activeBackgroundColor: '#f9f9f9',
            }}
        >
            <Tab.Screen name="Gasolina" component={Gasolina} />
            <Tab.Screen name="Álcool" component={Alcool} />
            <Tab.Screen name="Diesel" component={Diesel} />
            <Tab.Screen name="Gnv" component={Gnv} />
            <Tab.Screen name="Menu" component={Menu} />
        </Tab.Navigator >
    );
};