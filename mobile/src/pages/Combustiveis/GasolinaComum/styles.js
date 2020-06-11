import { StyleSheet } from 'react-native';

export default StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "#51397f",
    },

    list: {
        paddingHorizontal: 10,
        paddingTop: 20,
    },

    combustivelContainer: {
        backgroundColor: "#f9f9f9",
        marginBottom: 20,
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
        width: 24,
        height: 24,
        borderRadius: 50,
    },

    nome: {
        color: "#f9f9f9",
        fontSize: 14,
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
        color: "#7b287d",
        textAlign: "center",
        maxWidth: "80%",
        marginLeft: 5,
    },

    informacoes: {
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "space-between",
        marginBottom: 15,
    },

    valor: {
        fontSize: 20,
        color: "#7b287d",
    },

    distancia: {
        fontSize: 13,
        color: "#7b287d",
    },

    mapButton: {
        flexDirection: "row",
        alignItems: "center",
        paddingVertical: 4,
        paddingHorizontal: 5,
        borderRadius: 4,
        backgroundColor: "#7067cf",
    },

    textButton: {
        color: "#f9f9f9",
        fontSize: 13,
        marginLeft: 3,
    },
    
    atualizacao: {
        textAlign: "center",
        fontSize: 12,
        color: "#7b287d",
    },
});