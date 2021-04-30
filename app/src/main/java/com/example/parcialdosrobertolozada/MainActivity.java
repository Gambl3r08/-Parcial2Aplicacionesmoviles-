  package com.example.parcialdosrobertolozada;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


  public class MainActivity extends AppCompatActivity {
      //Salida
      private ListView txtSalida;
      //Datos
      private EditText serialEntrada;
      private EditText NombreMedicamentoEntrada;
      private EditText LaboratorioEntrada;
      private EditText FechaEntrada;
      private EditText TipoEntrada;
      private RadioGroup GrupoBotones;
      private RadioButton RadioAntibiotico;
      private RadioButton RadioAnalgesico;

      //Botones
      private Button btnAgregar;
      private Button btnListar;
      private Button btnActualizar;
      private Button btnEliminar;

      //SQL



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Texto Salida
        txtSalida = (ListView) findViewById(R.id.txtSalida);

        //DB
        //Datos
        serialEntrada = (EditText) findViewById(R.id.inputSerial);
        NombreMedicamentoEntrada = (EditText) findViewById(R.id.inputNombreMedicamento);
        LaboratorioEntrada = (EditText) findViewById(R.id.inputLaboratorio);
        FechaEntrada = (EditText) findViewById(R.id.inputFecha);

        GrupoBotones = (RadioGroup) findViewById(R.id.GrupoTipos);
        RadioAntibiotico = (RadioButton) findViewById(R.id.RadioAntibiotico);
        RadioAnalgesico = (RadioButton) findViewById(R.id.RadioAnalgesico);

        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnListar = (Button) findViewById(R.id.btnListar);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //SQL

                medicamentosController ControladorDB = new medicamentosController(v.getContext());

                //Datos
                String datoSerial = null;
                String datoNombreMedicamento = null;
                String datoLaboratorio = null;
                String datoFecha = null;
                String datoTipo = null;
                medicamentos datosMedicamentos = new medicamentos();

                datoSerial = serialEntrada.getEditableText().toString();
                datoNombreMedicamento = NombreMedicamentoEntrada.getEditableText().toString();
                datoLaboratorio = LaboratorioEntrada.getEditableText().toString();
                datoFecha = FechaEntrada.getEditableText().toString();
                if (RadioAnalgesico.isChecked()){
                    datoTipo = "Analgesico";
                } else if(RadioAntibiotico.isChecked()){
                    datoTipo = "Antibiotico";
                }

                //String LogSalida = "Serial: " + datoSerial + "\nNombre medicamento: " + datoNombreMedicamento + "\nLaboratorio: "+ datoLaboratorio + "\nFecha: " + datoFecha + "\nTipo: "+datoTipo ;



                boolean isNumeric = true;
                for(int i = 0; i<datoSerial.length(); i++){
                    if (!Character.isDigit(datoSerial.charAt(i))){
                        isNumeric = false;
                    }
                }

                if (isNumeric == true){
                    datosMedicamentos.setSerial(datoSerial);
                }

                datosMedicamentos.setNombreMedicamento(datoNombreMedicamento);
                datosMedicamentos.setLaboratorio(datoLaboratorio);

                /*
                boolean fechaValidada = true;
                int contValidar = 0;
                for(int i = 0; i < datoFecha.length(); i++){
                    contValidar += 1;
                    if (contValidar < 4){
                        if (!Character.isDigit(datoFecha.charAt(i))){
                            fechaValidada = false;
                        }
                    } else if (contValidar >=5 && contValidar < 7){
                        if (!Character.isDigit(datoFecha.charAt(i))){
                            fechaValidada = false;
                        }
                    } else if (contValidar > 7 && contValidar <=9){
                        if (!Character.isDigit(datoFecha.charAt(i))){
                            fechaValidada = false;
                        }
                    }
                }

                if (fechaValidada == true){
                    datosMedicamentos.setFecha(datoFecha);
                } */
                datosMedicamentos.setFecha(datoFecha);
                datosMedicamentos.setTipo(datoTipo);

                String logAgregado = ControladorDB.agregarMedicamento(datosMedicamentos);
                Toast.makeText(v.getContext(), "Dato agregado", Toast.LENGTH_LONG).show();


            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicamentosController ControladorDB = new medicamentosController(v.getContext());
                Cursor c = ControladorDB.ListarMedicamentos();
                ArrayList<String> datosSQL = new ArrayList<>();
                ArrayAdapter<String> adapter;

                if (c!=null){
                    String Cadena = "";
                    while(c.moveToNext()){
                        Cadena = "Serial :"  + c.getString(0)
                        + "\nNombre: " + c.getString(1)
                        + "\nLaboratorio: " + c.getString(2)
                        + "\nFecha: " + c.getString(3)
                        + "\nTipo: " + c.getString(4);
                        datosSQL.add(Cadena);
                    }

                    adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1, datosSQL );
                    txtSalida.setAdapter(adapter);


                }

            }
        });


        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicamentosController ControladorDB = new medicamentosController(v.getContext());
                //Datos
                String datoSerial = null;
                String datoNombreMedicamento = null;
                String datoLaboratorio = null;
                String datoFecha = null;
                String datoTipo = null;

                medicamentos datosMedicamentos = new medicamentos();

                datoSerial = serialEntrada.getEditableText().toString();
                datoNombreMedicamento = NombreMedicamentoEntrada.getEditableText().toString();
                datoLaboratorio = LaboratorioEntrada.getEditableText().toString();
                datoFecha = FechaEntrada.getEditableText().toString();
                if (RadioAnalgesico.isChecked()){
                    datoTipo = "Analgesico";
                } else if(RadioAntibiotico.isChecked()){
                    datoTipo = "Antibiotico";
                }

                boolean isNumeric = true;
                for(int i = 0; i<datoSerial.length(); i++){
                    if (!Character.isDigit(datoSerial.charAt(i))){
                        isNumeric = false;
                    }
                }

                if (isNumeric == true){
                    datosMedicamentos.setSerial(datoSerial);
                }

                datosMedicamentos.setNombreMedicamento(datoNombreMedicamento);
                datosMedicamentos.setLaboratorio(datoLaboratorio);
                datosMedicamentos.setFecha(datoFecha);
                datosMedicamentos.setTipo(datoTipo);

                String logActualizar = ControladorDB.ActualizarDato(datosMedicamentos);
                if(logActualizar == "1"){
                    Toast.makeText(v.getContext(), "Medicamento actualizado", Toast.LENGTH_LONG).show();
                } else if(logActualizar == "0"){
                    Toast.makeText(v.getContext(), "No se puedo actualizar el medicamento", Toast.LENGTH_LONG).show();
                }

            }
        });





        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicamentosController ControladorDB = new medicamentosController(v.getContext());
                String idEliminar = serialEntrada.getText().toString();
                String statusOutput = ControladorDB.EliminarDato(idEliminar);

                if (statusOutput == "1"){
                    Toast.makeText(v.getContext(), "Dato eliminado", Toast.LENGTH_LONG).show();
                } else if(statusOutput == "0"){
                    Toast.makeText(v.getContext(), "No se puedo eliminar el dato", Toast.LENGTH_LONG).show();
                }

            }
        });





        }
    }