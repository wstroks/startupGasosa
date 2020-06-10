import { StyleSheet } from 'react-native';

export default StyleSheet.create({
    container: {
        paddingVertical: 15,
        paddingHorizontal: 10,
        backgroundColor: "#51397f",

        flex: 1,
        justifyContent: "center",
    },

    input: {
        marginBottom: 15,
        paddingHorizontal: 15,
        borderColor: "#b7c0ee",
        borderRadius: 4,
        borderWidth: 1,
        justifyContent: "center",
        color: "#b7c0ee",
        height: 36,
    },

    button: {
        backgroundColor: "#b7c0ee",
        paddingHorizontal: 15,
        paddingVertical: 5,
        borderRadius: 4,
        justifyContent: "center",
        height: 36,
    },

    textButton: {
        textAlign: "center",
        color: "#330c2f",
    },

    resultado: {
        flexDirection: "column",
        borderRadius: 4,
        backgroundColor: "#f9f9f9",
        padding: 10,
        marginTop: 15,
    },

    span: {
        fontSize: 15,
        fontWeight: "bold",
        color: "#330c2f",
        textAlign: "center",
        marginBottom: 15,
    },

    p: {
        fontSize: 14,
        color: "#330c2f",
    }
});