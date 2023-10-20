package com.example.pmexamen69;

public class Paises {
    private Integer id;
    private String nombrePais;


    public Paises(Integer id, String nombrePais) {
        this.id = id;
        this.nombrePais = nombrePais;

    }

    public Paises() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
}