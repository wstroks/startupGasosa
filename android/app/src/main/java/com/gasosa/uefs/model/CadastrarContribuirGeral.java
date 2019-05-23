package com.gasosa.uefs.model;

public class CadastrarContribuirGeral {
    private String nome;

    private String data;
    private String usuario;
    private double gasolina;
    private double gasolinaAd;
    private double diesel;
    private double dieselAd;
    private double alcool;

    public CadastrarContribuirGeral() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public double getGasolina() {
        return gasolina;
    }

    public void setGasolina(double gasolina) {
        this.gasolina = gasolina;
    }

    public double getGasolinaAd() {
        return gasolinaAd;
    }

    public void setGasolinaAd(double gasolinaAd) {
        this.gasolinaAd = gasolinaAd;
    }

    public double getDiesel() {
        return diesel;
    }

    public void setDiesel(double diesel) {
        this.diesel = diesel;
    }

    public double getDieselAd() {
        return dieselAd;
    }

    public void setDieselAd(double dieselAd) {
        this.dieselAd = dieselAd;
    }

    public double getAlcool() {
        return alcool;
    }

    public void setAlcool(double alcool) {
        this.alcool = alcool;
    }
}
