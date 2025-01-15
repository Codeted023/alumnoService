package com.dfp.student_service.utils;

public class Constants {
    public static final String ESTADO_ACTIVO = "activo";
    public static final String ESTADO_INACTIVO = "inactivo";
    public static final String ERROR_ID = "El atributo id es obligatorio";
    public static final String ERROR_ID_REPETIDO = "El Id ya existe, " +
            "no esta permitido crear un nuevo alumno con este Id";
    public static final String ERROR_NOMBRE = "El atributo nombre es obligatorio";
    public static final String ERROR_APELLIDO = "El atributo apellido es obligatorio";
    public static final String ERROR_ESTADO = "El atributo estado debe ser 'activo' o 'inactivo'";
    public static final String ERROR_EDAD = "El atributo edad debe ser mayor a 0";
}
