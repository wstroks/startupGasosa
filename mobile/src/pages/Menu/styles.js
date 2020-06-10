import { StyleSheet } from 'react-native';

export default StyleSheet.create({
    menuContainer: {
        flex: 1,
        alignItems: "center",
        justifyContent: "center",
        paddingHorizontal: 10,
        paddingVertical: 15,
        backgroundColor: "#51397f",
    },

    itemButton: {
        width: "100%",
        height: 36,
        justifyContent: "center",
        borderRadius: 4,
        backgroundColor: "#b7c0ee",
        paddingHorizontal: 15,
        paddingVertical: 7,
        margin: 5,
    },

    textButton: {
        fontSize: 13,
        textAlign: "center",
        color: "#330c2f",
    },
});