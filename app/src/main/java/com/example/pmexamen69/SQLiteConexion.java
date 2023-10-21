package com.example.pmexamen69;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteConexion extends SQLiteOpenHelper
{
    public SQLiteConexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        // Crear los objectos de base de datos
        sqLiteDatabase.execSQL(Transacciones.CreateTablePersonas);
        sqLiteDatabase.execSQL(Transacciones.CreateTablePaises);
        insertarPaisesIniciales(sqLiteDatabase);

    }
    private void insertarPaisesIniciales(SQLiteDatabase db) {
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.pais, "Honduras");
        db.insert(Transacciones.Tabla2, null, valores);

        valores.put(Transacciones.pais, "El Salvador");
        db.insert(Transacciones.Tabla2, null, valores);

        valores.put(Transacciones.pais, "Guatemala");
        db.insert(Transacciones.Tabla2, null, valores);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL(Transacciones.DropTablePersonas);
        sqLiteDatabase.execSQL(Transacciones.DropTablePaises);
        onCreate(sqLiteDatabase);

    }






}
