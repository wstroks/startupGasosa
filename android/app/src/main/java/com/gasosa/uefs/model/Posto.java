package com.gasosa.uefs.model;

public class Posto {
    private String nome;
    private Double gasolina;
    private Double alcool;
    private Double diesel;
    private Double gasolinaAd;
    private Double dieselAd;
    private String bairro;
    private String latitude;
    private String logintude;
    private String data;
    private String link;
    private String nome_filtro;
    private String logo;

    public Posto() {

    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Double getGasolinaAd() {
        return gasolinaAd;
    }

    public void setGasolinaAd(Double gasolinaAd) {
        this.gasolinaAd = gasolinaAd;
    }

    public String getNome_filtro() {
        return nome_filtro;
    }

    public void setNome_filtro(String nome_filtro) {
        this.nome_filtro = nome_filtro;
    }

    public Double getDieselAd() {
        return dieselAd;
    }

    public void setDieselAd(Double dieselAd) {
        this.dieselAd = dieselAd;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getGasolina() {
        return gasolina;
    }

    public void setGasolina(Double gasolina) {
        this.gasolina = gasolina;
    }

    public Double getAlcool() {
        return alcool;
    }

    public void setAlcool(Double alcool) {
        this.alcool = alcool;
    }

    public Double getDiesel() {
        return diesel;
    }

    public void setDiesel(Double diesel) {
        this.diesel = diesel;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLogintude() {
        return logintude;
    }

    public void setLogintude(String logintude) {
        this.logintude = logintude;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
