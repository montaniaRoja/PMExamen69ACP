package com.example.pmexamen69;

public class Contactos {

    private Integer id;
    private String nombres;
    private String telefono;
    private String pais;
    private byte[] foto;

    private String nota;

    public Contactos(Integer id, String nombres, String telefono, String pais, byte foto[]){
        this.id=id;
        this.nombres=nombres;
        this.telefono=telefono;
        this.pais=pais;
        this.foto=foto;

    }

    public Contactos(){

    }

    public Integer getId() {
        return id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
