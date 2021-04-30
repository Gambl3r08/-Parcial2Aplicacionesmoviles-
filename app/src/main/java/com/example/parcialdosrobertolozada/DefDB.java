package com.example.parcialdosrobertolozada;

public class DefDB {
    public static final String DATABASE_NAME = "medicamentos";
    public static final String TABLE_NAME = "medicamentos";
    public static final String COLUMN_SERIAL = "serial";
    public static final String COLUMN_NOMBRE = "nombremedicamento";
    public static final String COLUMN_LABORATORIO = "laboratorio";
    public static final String COLUMN_FECHA = "date";
    public static final String COLUMN_TIPO = "tipo";

    public static final String create_tabla_est = "CREATE TABLE IF NOT EXISTS " +
            DefDB.TABLE_NAME + " ( " +
            DefDB.COLUMN_SERIAL + " text primary key, " +
            DefDB.COLUMN_NOMBRE + " text, " +
            DefDB.COLUMN_LABORATORIO + " text," +
            DefDB.COLUMN_FECHA + " text, " +
            DefDB.COLUMN_TIPO + " text" +
            ");";

}
