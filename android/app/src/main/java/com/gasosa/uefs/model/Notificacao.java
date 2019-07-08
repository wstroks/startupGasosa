package com.gasosa.uefs.model;

public class Notificacao {

    private String titulo;
    private String corpo;
    private String link;
    private String data;

    public Notificacao() {

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
