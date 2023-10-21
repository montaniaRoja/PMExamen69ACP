package com.example.pmexamen69;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView listView;
    ArrayList<Contactos> listContactos;

    ArrayList<String> arregloContactos;
    Button btnCompartir, btnEliminar, btnVerFoto, btnActualizar;
    int selectedPosition = ListView.INVALID_POSITION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        conexion = new SQLiteConexion(this, Transacciones.namedb, null, 1);
        listView = (ListView) findViewById(R.id.contactList);
        btnCompartir = (Button) findViewById(R.id.btnCompartir);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnVerFoto = (Button) findViewById(R.id.btnVerFoto);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí debes obtener el ID del contacto seleccionado
                // Puedes obtenerlo de la lista o interfaz de usuario
                int selectedContactId = obtenerIDContactoSeleccionado();

                // Llama a la función para eliminar el contacto
                eliminarContacto(selectedContactId);
            }
        });

        arregloContactos = new ArrayList<String>();

        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, arregloContactos);
        listView.setAdapter(adp);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedContact = arregloContactos.get(i);
                showConfirmationDialog(selectedContact);
            }
        });

        btnCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedItemPosition = listView.getCheckedItemPosition();

                if (selectedItemPosition != AdapterView.INVALID_POSITION) {
                    String selectedContact = arregloContactos.get(selectedItemPosition);
                    compartirContacto(selectedContact);
                }
            }
        });




        GetPersons();
       // ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arregloContactos);
       // listView.setAdapter(adp);
    }

    private int obtenerIDContactoSeleccionado() {
        int selectedContactId = -1; // Valor predeterminado en caso de error o ningún elemento seleccionado
        int selectedItemPosition = listView.getCheckedItemPosition();

        if (selectedItemPosition != AdapterView.INVALID_POSITION) {
            String selectedContact = arregloContactos.get(selectedItemPosition);
            // Dividir la cadena en partes: ID - Nombre Teléfono
            String[] parts = selectedContact.split("-");
            if (parts.length >= 1) {
                try {
                    selectedContactId = Integer.parseInt(parts[0].trim()); // Extraer el ID
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        return selectedContactId;
    }


    private void compartirContacto(String selectedContact) {
        // Aquí puedes definir el formato de cómo deseas compartir la información del contacto.
        String contenidoParaCompartir = "Contacto seleccionado: " + selectedContact;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, contenidoParaCompartir);

        startActivity(Intent.createChooser(intent, "Compartir contacto"));
    }


    private void showConfirmationDialog(String selectedContact) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Llamar a " + selectedContact);
        builder.setMessage("¿Desea llamar a este contacto?");

        builder.setPositiveButton("Llamar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Obtiene el número de teléfono de selectedContact
                String[] parts = selectedContact.split("-");
                String telefono = parts[1].trim();

                // Inicia la llamada
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telefono));
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
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

    private void eliminarContacto(final int contactId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar contacto");
        builder.setMessage("¿Está seguro de que desea eliminar este contacto?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Si el usuario confirma la eliminación, procedemos a eliminar el contacto
                SQLiteDatabase db = conexion.getWritableDatabase();
                String[] whereArgs = { String.valueOf(contactId) };

                // Ejecuta la consulta para eliminar el contacto por su ID
                db.delete(Transacciones.Tabla1, Transacciones.id + " = ?", whereArgs);

                db.close();
                arregloContactos.clear();
                GetPersons();

                // Notifica al adaptador que los datos han cambiado
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Si el usuario cancela la eliminación, simplemente cerramos el cuadro de diálogo
                dialog.dismiss();
            }
        });

        builder.show();
    }



}