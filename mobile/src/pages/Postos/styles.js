import { StyleSheet } from 'react-native';
import Constants from 'expo-constants';

export default StyleSheet.create({
    container: {
        flex: 1,
        paddingHorizontal: 24,
        paddingTop: Constants.statusBarHeight + 20,
    },

    pageName:{
        textAlign: "center",
        marginBottom: 24,
    },

    lineButtons:{
        flex: 1,
        flexDirection: "column",
        justifyContent: "space-around",
    },
});