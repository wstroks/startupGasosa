import { StyleSheet } from 'react-native';

export default StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: "center",
        backgroundColor: "#330c2f",
        padding: 15,
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

    verNoMapa: {
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "center",
        padding: 5,
        borderRadius: 4,
        color: "#f9f9f9",
        backgroundColor: "#7067cf",
        marginBottom: 10,
        width: 120,
    },

    combustivel: {
        width: "100%",
        flexDirection: "row",
        justifyContent: "space-between",
    },

    textButton: {
        fontSize: 12,
        fontWeight: "bold",
        color: "#f9f9f9",
        marginLeft: 5,
    },
});