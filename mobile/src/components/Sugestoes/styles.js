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
        color: "#b7c0ee",
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

    span: {
        fontSize: 16,
        color: "#b7c0ee",
        textAlign: "center",
        marginBottom: 10,
    },
});