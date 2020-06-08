import { StyleSheet } from 'react-native';

export default StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "#330c2f",
    },

    busca: {
        justifyContent: "center",
        marginVertical: 15,
        width: "100%",
        height: 32,
        position: "relative",
        paddingHorizontal: 10,
    },

    input: {
        width: "100%",
        height: 32,
        paddingLeft: 30,
        backgroundColor: "transparent",
        color: "#b7c0ee",
        borderWidth: 2,
        borderRadius: 4,
        borderColor: "#b7c0ee",
    },

    svg: {
        position: "absolute",
        left: 20,
    },

    list: {
        paddingHorizontal: 10,
    },

    postosContainer: {
        backgroundColor: "#f9f9f9",
        marginBottom: 20,
        borderRadius: 4,
    },

    header: {
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "center",
        backgroundColor: "#7b287d",
        padding: 10,
        borderRadius: 4,
        borderBottomLeftRadius: 0,
        borderBottomRightRadius: 0,
    },

    bandeira: {
        width: 24,
        height: 24,
        borderRadius: 50,
        marginRight: 10,
    },

    nome: {
        color: "#f9f9f9",
        fontSize: 14,
        fontWeight: "bold",
        textAlign: "center",
    },

    body: {
        backgroundColor: "#f9f9f9",
        padding: 10,
        borderRadius: 4,
        borderTopLeftRadius: 0,
        borderTopRightRadius: 0,
    },

    endereco: {
        fontSize: 13,
        fontWeight: "bold",
        textAlign: "center",
        color: "#7b287d",
        marginBottom: 15,
    },

    acessoButton: {
        alignItems: "center",
        justifyContent: "center",
        paddingHorizontal: 10,
        paddingVertical: 1,
        borderWidth: 1,
        borderColor: "#7b287d",
        borderRadius: 4,
    },

    textButton: {
        fontSize: 12,
        fontWeight: "bold",
        color: "#7b287d",
    },
});