package edu.eci.mcsw.services;

import com.google.gson.Gson;
import edu.eci.mcsw.model.Cuenta;
import edu.eci.mcsw.model.Transaccion;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class TransaccionServices {

    public static void registrarTransaccion(Connection con, Transaccion transaccion) throws SQLException {

        con.setAutoCommit(true);
        PreparedStatement insertTransaccion = null;

        String insertStatment = "INSERT INTO TRANSACCION VALUES (?,?,?,?,?,?,?,?)";

        insertTransaccion = con.prepareStatement(insertStatment);
        insertTransaccion.setString(1, transaccion.getIdentificador());
        insertTransaccion.setString(2, transaccion.getOrigen());
        insertTransaccion.setString(3, transaccion.getDestinatario());
        insertTransaccion.setFloat(4, transaccion.getSaldoatransferir());
        insertTransaccion.setTimestamp(5, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
        insertTransaccion.setString(6,  transaccion.getDetalle());
        insertTransaccion.setBoolean(7, transaccion.isAprobacion());
        insertTransaccion.setBoolean(8, transaccion.isIntrabancaria());

        insertTransaccion.execute();
        con.setAutoCommit(false);
        con.commit();

        if (transaccion.isIntrabancaria()){
            CuentaServices.actualizarSaldo(con,transaccion.getOrigen(),transaccion.getSaldoatransferir()*-1);
            CuentaServices.actualizarSaldo(con,transaccion.getDestinatario(),transaccion.getSaldoatransferir());
        }else{
            CuentaServices.actualizarSaldo(con,transaccion.getOrigen(),transaccion.getSaldoatransferir()*-1);
        }

    }

    public static Set<Map<String,String>> getTransaccionById(Connection con , String usuario) throws SQLException {

        PreparedStatement selectTransaccion = null;

        String selectStatment = "SELECT origen,destinatario,saldoatransferir,fecha,detalle FROM TRANSACCION WHERE origen IN (SELECT numerodecuenta FROM CUENTA WHERE usuario='"+usuario+"') or destinatario IN (SELECT numerodecuenta FROM CUENTA WHERE usuario='"+usuario+"') ";
        selectTransaccion = con.prepareStatement(selectStatment);
        Set<Map<String,String>> jsongrande = new HashSet<>();

        ResultSet resultado = selectTransaccion.executeQuery();
        ResultSetMetaData rsmd = resultado.getMetaData();

        while(resultado.next()){
            Map<String, String> jsonpequeño = new HashMap<>();
            jsonpequeño.put(rsmd.getColumnName(1), resultado.getString("origen"));
            jsonpequeño.put(rsmd.getColumnName(2), resultado.getString("destinatario"));
            jsonpequeño.put(rsmd.getColumnName(3), resultado.getString("saldoatransferir"));
            jsonpequeño.put(rsmd.getColumnName(4), resultado.getString("fecha"));
            jsonpequeño.put(rsmd.getColumnName(5), resultado.getString("detalle"));

            jsongrande.add(jsonpequeño);
        }
        System.out.println(jsongrande);
        return jsongrande;
    }


}
