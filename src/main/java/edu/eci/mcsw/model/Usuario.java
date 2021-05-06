package edu.eci.mcsw.model;

import java.util.ArrayList;
import java.util.Collection;


public class Usuario {


    private String apellido;
    private String nombre;
    private String celular;
    private String correo;


    private String cedula;

    private String usuarioid;

    private String pwd;

    private Role rol;

    public Usuario() {
    }

    public Usuario(String usuarioid, String nombre, String apellido, String correo, String pwd, String cedula, String celular,Role rol) {
        this.usuarioid = usuarioid;
        this.nombre = nombre;
        this.celular = celular;
        this.apellido = apellido;
        this.correo = correo;
        this.pwd = pwd;
        this.cedula = cedula;
        this.rol = rol;
    }

    public String getCelular() {
        return celular;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(String usuarioid) {
        this.usuarioid = usuarioid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", celular='" + celular + '\'' +
                ", correo='" + correo + '\'' +
                ", cedula='" + cedula + '\'' +
                ", usuarioid='" + usuarioid + '\'' +
                ", pwd='" + pwd + '\'' +
                ", rol=" + rol +
                '}';
    }
}
