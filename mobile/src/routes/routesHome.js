import React from 'react';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { MaterialCommunityIcons } from '@expo/vector-icons';

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
                    // height: 54,
                },
                tabStyle: {
                    height: 54,
                    padding: 5,
                    justifyContent: "center",
                    alignItems: "center",
                },
                activeTintColor: "#330c2f",
                inactiveTintColor: "#330c2f",
                activeBackgroundColor: "#f0f0f0",
            }}
        >
            <Tab.Screen
                name="Gasolina"
                component={Gasolina}
                options={{
                    tabBarIcon: ({ color, size }) => (
                        <MaterialCommunityIcons name="gas-station" color={color} size={size} />
                    ),
                }}
            />

            <Tab.Screen
                name="Álcool"
                component={Alcool}
                options={{
                    tabBarIcon: ({ color, size }) => (
                        <MaterialCommunityIcons name="gas-station" color={color} size={size} />
                    ),
                }}
            />

            <Tab.Screen
                name="Diesel"
                component={Diesel}
                options={{
                    tabBarIcon: ({ color, size }) => (
                        <MaterialCommunityIcons name="gas-station" color={color} size={size} />
                    ),
                }}
            />
            <Tab.Screen
                name="Gnv"
                component={Gnv}
                options={{
                    tabBarIcon: ({ color, size }) => (
                        <MaterialCommunityIcons name="gas-station" color={color} size={size} />
                    ),
                }}
            />

            <Tab.Screen
                name="Menu"
                component={Menu}
                options={{
                    tabBarIcon: ({ color, size }) => (
                        <MaterialCommunityIcons name="menu" color={color} size={size} />
                    ),
                }}
            />
        </Tab.Navigator >
    );
};