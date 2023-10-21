package com.example.pmexamen69;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView listView;
    ArrayList<Contactos> listContactos;

    ArrayList<String> arregloContactos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        conexion = new SQLiteConexion(this, Transacciones.namedb, null, 1);
        listView = (ListView) findViewById(R.id.contactList);

        // Inicializa el ArrayList arregloPersonas
        arregloContactos = new ArrayList<String>();

        GetPersons();
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arregloContactos);
        listView.setAdapter(adp);
    }


    private void GetPersons() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Contactos contacto = null;
        listContactos = new ArrayList<Contactos>();

        Cursor cursor = db.rawQuery(Transacciones.SelectTablePersonas, null);
        while (cursor.moveToNext()) {
            contacto = new Contactos();
            contacto.setId(cursor.getInt(0));
            contacto.setNombres(cursor.getString(1));
            contacto.setTelefono(cursor.getString(2));
            contacto.setNota(cursor.getString(3));
            contacto.setPais(cursor.getString(4));

            listContactos.add(contacto);

        }

        cursor.close();

        FillList();


    }

    private void FillList() {
        for (int i = 0; i < listContactos.size(); i++) {
            arregloContactos.add(listContactos.get(i).getId() + "-" +
                    listContactos.get(i).getNombres() + " " +
                    listContactos.get(i).getTelefono());

        }


    }
}