import React from 'react';
import { View, Text } from 'react-native';

import styles from './styles';

export default function Sobre () {
    return (
        <View style={styles.container}>
            <Text style={styles.text}>
                Praesent tempus fringilla risus in rhoncus. Mauris congue risus a nisl feugiat sodales. Mauris rutrum erat quis purus semper, a pretium quam pellentesque. Nunc egestas orci eros, a gravida ligula condimentum in. Duis sollicitudin egestas lacinia. Vestibulum malesuada elit ut quam mollis, non semper massa rhoncus. In hendrerit efficitur imperdiet. Morbi et enim massa. Suspendisse sem metus, facilisis in finibus sed, pretium vel dolor. Praesent nec neque enim.
            </Text>
        </View>
    );
};