package edu.eci.mcsw.model;

import java.util.Date;
import java.util.Calendar;

public class Transaccion {

    private String identificador;
    private String origen;
    private String destinatario;
    private float saldoatransferir;
    private Date fecha;
    private String detalle;
    private boolean aprobacion;
    private boolean intrabancaria;

    public Transaccion() { }

    public Transaccion(String identificador, String origen, String destinatario ,float saldoatransferir, boolean aprobacion, String detalle, boolean intrabancaria) {
        this.identificador = identificador;
        this.origen = origen;
        this.destinatario = destinatario;
        this.saldoatransferir = saldoatransferir;
        this.aprobacion = aprobacion;
        this.detalle = detalle;
        this.intrabancaria = intrabancaria;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = Calendar.getInstance().getTime();
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public float getSaldoatransferir() {
        return saldoatransferir;
    }

    public void setSaldoatransferir(float saldoatransferir) {
        this.saldoatransferir = saldoatransferir;
    }

    public boolean isAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(boolean aprobacion) {
        this.aprobacion = aprobacion;
    }

    public boolean isIntrabancaria() {
        return intrabancaria;
    }

    public void setIntrabancaria(boolean intrabancaria) {
        this.intrabancaria = intrabancaria;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "identificador='" + identificador + '\'' +
                ", origen='" + origen + '\'' +
                ", destinatario='" + destinatario + '\'' +
                ", saldoatransferir=" + saldoatransferir +
                ", detalle='" + detalle + '\'' +
                ", aprobacion=" + aprobacion +
                ", intrabancaria=" + intrabancaria +
                '}';
    }
}
