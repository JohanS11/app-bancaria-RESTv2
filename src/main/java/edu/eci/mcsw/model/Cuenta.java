package edu.eci.mcsw.model;

public class Cuenta {

    private String numerodecuenta;
    private String tipodecuenta;
    private float saldo;
    private String usuario;

    public Cuenta() {

    }

    public Cuenta(String numerodecuenta, String tipodecuenta, float saldo, String usuario) {

        this.numerodecuenta = numerodecuenta;
        this.tipodecuenta = tipodecuenta;
        this.saldo = saldo;
        this.usuario = usuario;
    }

    public String getNumerodecuenta() {
        return numerodecuenta;
    }

    public void setNumerodecuenta(String numerodecuenta) {
        this.numerodecuenta = numerodecuenta;
    }

    public String getTipodecuenta() {
        return tipodecuenta;
    }

    public void setTipodecuenta(String tipodecuenta) {
        this.tipodecuenta = tipodecuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numerodecuenta='" + numerodecuenta + '\'' +
                ", tipodecuenta='" + tipodecuenta + '\'' +
                ", saldo=" + saldo +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
