import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

export default function App() {
  return (
    <View style={styles.container}>
      <Text style={styles.texto}>Gasosa!</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#7b287d',
    alignItems: 'center',
    justifyContent: 'center',
  },

  texto:{
    color: '#fff',
    fontSize: 20,
    textAlign: 'center',
  },
});
