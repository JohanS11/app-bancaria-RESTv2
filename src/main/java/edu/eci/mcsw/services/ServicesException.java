package edu.eci.mcsw.services;

public class ServicesException extends Exception {

    public static final String USUARIO_NO_REGISTRADO_EN_SISTEMA = "usuario no registrado en sistema" ;

    public ServicesException(){
        super();
    }
    public ServicesException(String message){
        super(message);
    }
}
