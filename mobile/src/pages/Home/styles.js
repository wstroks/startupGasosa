import { StyleSheet } from 'react-native';

export default StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "#330c2f",
    },

    list:{    
        paddingHorizontal: 10,
        paddingVertical: 15,
    },

    combustivelContainer: {
        backgroundColor: "#f9f9f9",
        marginTop: 20,
        borderRadius: 4,
    },

    header: {
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "space-between",
        backgroundColor: "#7b287d",
        padding: 10,
        borderRadius: 4,
        borderBottomLeftRadius: 0,
        borderBottomRightRadius: 0,
    },

    bandeira: {
        width: 30,
        height: 30,
        borderRadius: 50,
    },

    body: {
        backgroundColor: "#f9f9f9",
        padding: 10,
        borderRadius: 4,
        borderTopLeftRadius: 0,
        borderTopRightRadius: 0,
    },
});