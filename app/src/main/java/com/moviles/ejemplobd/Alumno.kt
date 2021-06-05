package com.moviles.ejemplobd

class Alumno(id:String, nombre:String, sexo:String, apellidos:String, direccion:String) {
    var id:String? = null
    var nombre:String? = null
    var sexo:String? = null
    var apellidos:String? = null
    var direccion:String? = null

    init {
        this.id = id
        this.nombre = nombre
        this.sexo =  sexo
        this.apellidos =  apellidos
        this.direccion =  direccion
    }
}