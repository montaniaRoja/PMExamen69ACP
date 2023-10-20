package com.example.pmexamen69;

public class Transacciones
{
    // Nombre de la base de datos
    public static final String namedb = "contactos";

    //Tablas de la base de datos
    public static final String Tabla1  = "personas";
    public static final String Tabla2  = "paises";

    // Campos de la tabla
    public static final String id = "id";

   public static final String nombres = "nombres";
    public static final String telefono = "telefono" ;
    public static final String notas = "notas";


    public static final String id_pais = "id_pais";
    public static final String pais = "pais";

    // Consultas de Base de datos
    //ddl
    public static final String CreateTablePersonas = "CREATE TABLE personas "+
            "( id INTEGER PRIMARY KEY AUTOINCREMENT, nombres TEXT, telefono TEXT, notas TEXT)";

    public static final String DropTablePersonas  = "DROP TABLE IF EXISTS personas";

    public static final String SelectTablePersonas = "SELECT * FROM " + Transacciones.Tabla1;


    public static final String CreateTablePaises = "CREATE TABLE paises "+
            "( id_pais INTEGER PRIMARY KEY AUTOINCREMENT, pais TEXT)";


    public static final String DropTablePaises = "DROP TABLE IF EXISTS paises";

    //dml

    public static final String SelectTablePaises = "SELECT * FROM " + Transacciones.Tabla2;

}
