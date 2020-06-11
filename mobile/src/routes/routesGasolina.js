import React from 'react';
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs';

import Comum from '../pages/Combustiveis/GasolinaComum';
import Aditivada from '../pages/Combustiveis/GasolinaAditivada';

const Gasolinas = createMaterialTopTabNavigator();

export default function RoutesGasolina () {
    return (
        <Gasolinas.Navigator
            tabBarOptions={{
                tabStyle: {
                    padding: 5,
                    justifyContent: "center",
                    alignItems: "center",
                },
                activeTintColor: "#330c2f",
                inactiveTintColor: "#330c2fcc",
                indicatorStyle: {
                    backgroundColor: "#330c2f",
                },
            }}
        >
            <Gasolinas.Screen name="Comum" component={Comum} />
            <Gasolinas.Screen name="Aditivada" component={Aditivada} />
        </Gasolinas.Navigator>
    );
}