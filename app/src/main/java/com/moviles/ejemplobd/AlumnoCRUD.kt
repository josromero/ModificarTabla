package com.moviles.ejemplobd

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.constraintlayout.solver.state.State

class AlumnoCRUD(context: Context) {

    private var helper:DataBaseHelper? = null

    init {
        helper = DataBaseHelper(context)
    }

    fun nuevoAlumno(item:Alumno){
        //habilita la DB para poder acceder
        //en modo escritura
        val db:SQLiteDatabase = helper?.writableDatabase!!

        //ContentValues = clase que permite agrupar información para poder mapearla
        //mapeo de las columnas con valores a insertar
        val values = ContentValues()
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_SEXO, item.sexo)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_APELLIDOS, item.apellidos)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_DIRECCION, item.direccion)


        //insertar una nueva fila en la tabla
        val newRowId = db.insert(AlumnosContract.Companion.Entrada.NOMBRE_TABLA, null,values)

        db.close()
    }

    fun mostrarAlumnos():ArrayList<Alumno>{

        val items:ArrayList<Alumno> = ArrayList()

        //Abrir DB en modo lectura
        val db:SQLiteDatabase = helper?.readableDatabase!!

        //especificar columnas que quiere consultar
        val columnas = arrayOf(AlumnosContract.Companion.Entrada.COLUMNA_ID,AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE, AlumnosContract.Companion.Entrada.COLUMNA_SEXO,
            AlumnosContract.Companion.Entrada.COLUMNA_APELLIDOS, AlumnosContract.Companion.Entrada.COLUMNA_DIRECCION)

        //Crear un cursor para recorrer la tabla (definicion)
        val c:Cursor = db.query(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            null,
            null,
            null,
            null,
            null
        )

        //hacer el recorrido del cursor en la tabla
        while(c.moveToNext()){
            items.add(Alumno(
                c.getString(c.getColumnIndexOrThrow((AlumnosContract.Companion.Entrada.COLUMNA_ID))),
                c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE)),
                c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_SEXO)),
                c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_APELLIDOS)),
                c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_DIRECCION))
            ))
        }

        //Cerrar DB
        db.close()

        return items
    }

    fun mostrarAlumnos(id:String):Alumno{
        var item:Alumno? = null

        val db:SQLiteDatabase = helper?.readableDatabase!!

        val columnas = arrayOf(AlumnosContract.Companion.Entrada.COLUMNA_ID,AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE,AlumnosContract.Companion.Entrada.COLUMNA_SEXO,
            AlumnosContract.Companion.Entrada.COLUMNA_APELLIDOS, AlumnosContract.Companion.Entrada.COLUMNA_DIRECCION)

        val c:Cursor = db.query(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            " id = ?",
            arrayOf(id),
            null,
            null,
            null
        )

        while (c.moveToNext()){
            item = Alumno(c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_ID)),
            c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE)),
            c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_SEXO)),
            c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_APELLIDOS)),
            c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_DIRECCION)))
        }

        db.close()

        return item!!
    }

    fun actualizarAlumno(item:Alumno){

        val db:SQLiteDatabase = helper?.writableDatabase!!

        val values = ContentValues()
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_SEXO, item.sexo)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_SEXO, item.apellidos)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_SEXO, item.direccion)

        db.update(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            values,
            "id = ?",
            arrayOf(item.id)
        )

        db.close()
    }

    fun eliminarAlumnos(item:Alumno){

        val db:SQLiteDatabase = helper?.writableDatabase!!

        db.delete(AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            "id = ?",
            arrayOf(item.id))

        db.close()
    }
}