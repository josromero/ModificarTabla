package com.moviles.ejemplobd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper (context: Context):SQLiteOpenHelper(context,AlumnosContract.Companion.Entrada.NOMBRE_TABLA, null,AlumnosContract.Companion.VERSION) {

    companion object{
        val CREATE_ALUMNOS_TABLE = "CREATE TABLE" + AlumnosContract.Companion.Entrada.NOMBRE_TABLA +
                " (" + AlumnosContract.Companion.Entrada.COLUMNA_ID + "TEXT PRIMARY KEY," +
                AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE + "TEXT," +
                AlumnosContract.Companion.Entrada.COLUMNA_SEXO + "TEXT," +
                AlumnosContract.Companion.Entrada.COLUMNA_APELLIDOS + "TEXT," +
                AlumnosContract.Companion.Entrada.COLUMNA_DIRECCION + "TEXT )"

        val REMOVE_ALUMNOS_TABLA = "DROP TABLE IF EXISTS " +AlumnosContract.Companion.Entrada.NOMBRE_TABLA
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //Ejecute el siguiente SQL
        db?.execSQL(CREATE_ALUMNOS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(REMOVE_ALUMNOS_TABLA)
        onCreate(db)
    }

}