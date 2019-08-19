package com.gasosa.uefs.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class PostoGasDistancia {
    private String nome;
    private Double gas;
    private String distancia;

    private String bairro;

    private String data;
    private String link;
    private String logo;

    public PostoGasDistancia() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getGas() {
        return gas;
    }

    public void setGas(Double gas) {
        this.gas = gas;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }



    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int compareTO(PostoGasDistancia o){
        return distancia.compareTo(o.getDistancia());
    }
}
