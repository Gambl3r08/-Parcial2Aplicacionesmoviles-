package com.example.parcialdosrobertolozada;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class medicamentosController {

    private BaseDatos db;
    private Context c;
    public medicamentosController(Context c){
        this.db = new BaseDatos(c, 1);
        this.c = c;
    }


    public String agregarMedicamento(medicamentos md){
        try {
            SQLiteDatabase sql = db.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DefDB.COLUMN_SERIAL, md.getSerial());
            cv.put(DefDB.COLUMN_NOMBRE, md.getNombreMedicamento());
            cv.put(DefDB.COLUMN_LABORATORIO, md.getLaboratorio());
            cv.put(DefDB.COLUMN_FECHA, md.getFecha());
            cv.put(DefDB.COLUMN_TIPO, md.getTipo());
            long res = sql.insert(DefDB.TABLE_NAME, null, cv);
            String respuesta = "Res: " + res;

            return respuesta ;

        }
        catch (Exception ex){
            String err = ex.getMessage();
            return err;
        }
    }

    public Cursor ListarMedicamentos(){
        try {
            SQLiteDatabase data = db.getReadableDatabase();
            Cursor cur = data.query(DefDB.TABLE_NAME, null, null, null, null, null, null);
            return cur;
        }
        catch (Exception ex){
            Toast.makeText(c,"Error en la consulta",Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public String ActualizarDato(medicamentos md){
        SQLiteDatabase sql = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DefDB.COLUMN_NOMBRE, md.getNombreMedicamento());
        cv.put(DefDB.COLUMN_LABORATORIO, md.getLaboratorio());
        cv.put(DefDB.COLUMN_FECHA, md.getFecha());
        cv.put(DefDB.COLUMN_TIPO, md.getTipo());
        long state = sql.update(DefDB.TABLE_NAME, cv, DefDB.COLUMN_SERIAL+" = ?",new String[]{md.getSerial()});
        String res = ""+state;
        return res;
    }

    public String EliminarDato(String serial){
        SQLiteDatabase sql = db.getWritableDatabase();

        //long res = sql.insert(DefDB.TABLE_NAME, null, cv);
        long resp = sql.delete(DefDB.TABLE_NAME, DefDB.COLUMN_SERIAL + "="+serial, null);
        String res = "Retorno" + resp;
        return res;

    }

    


}
