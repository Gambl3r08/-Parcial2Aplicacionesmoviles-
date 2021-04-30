package com.example.parcialdosrobertolozada;

public class medicamentos {
    private String serial;
    private String nombreMedicamento;
    private String laboratorio;
    private String fecha;
    private String tipo;

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }


    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSerial() {
        return serial;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }


}
