import { StyleSheet } from 'react-native';

export default StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: "center",
        backgroundColor: "#51397f",
        padding: 15,
        position: "relative",
    },

    back: {
        position: "absolute",
        top: 15,
        left: 10,
    },

    postoContainer: {
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
        fontSize: 15,
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

    viewEndereco: {
        flexDirection: "row",
        justifyContent: "center",
        marginBottom: 10,
    },

    endereco: {
        fontSize: 13,
        fontWeight: "bold",
        color: "#7b287d",
        textAlign: "center",
        maxWidth: "80%",
        marginLeft: 5,
    },

    distancia: {
        fontSize: 13,
        fontWeight: "bold",
        textAlign: "center",
        color: "#7b287d",
        marginBottom: 10,
    },

    verNoMapa: {
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "center",
        alignSelf: "center",
        paddingHorizontal: 10,
        paddingVertical: 5,
        borderRadius: 4,
        color: "#f9f9f9",
        backgroundColor: "#7067cf",
        marginBottom: 10,
    },

    combustivel: {
        flexDirection: "row",
        justifyContent: "space-between",
        marginBottom: 5,
    },

    nomeCombustivel: {
        fontSize: 12,
        color: "#7b287d",
        fontWeight: "bold",
    },

    valorCombustivel: {
        fontSize: 12,
        color: "#7b287d",
        fontWeight: "bold",
    },

    textButton: {
        fontSize: 12,
        fontWeight: "bold",
        color: "#f9f9f9",
        marginLeft: 5,
    },

    compartilhar: {
        fontSize: 12,
        fontWeight: "bold",
        color: "#7b287d",
        textAlign: "center",
        marginVertical: 10,
    },

    socialMedia: {
        flexDirection: "row",
        justifyContent: "center",
    },

    media: {
        alignItems: "center",
        justifyContent: "center",
        backgroundColor: "#7067cf",
        borderRadius: 4,
        marginHorizontal: 5,
        width: 28,
        height: 28,
    },
});