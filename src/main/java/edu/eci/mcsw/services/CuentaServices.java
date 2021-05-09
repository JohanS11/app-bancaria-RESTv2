package edu.eci.mcsw.services;

import edu.eci.mcsw.model.Cuenta;

import java.sql.*;
import java.util.*;

public class CuentaServices {

    public static List<String> verCuenta(Connection con , String cuenta) throws SQLException {


        List<String> np=new LinkedList<>();

        PreparedStatement getcuenta = null;
        System.out.println(cuenta);
        String consultarcuenta = "SELECT * FROM CUENTA where numerodecuenta="+"'"+cuenta+"'";
        getcuenta = con.prepareStatement(consultarcuenta);

        ResultSet resultado = getcuenta.executeQuery();
        while(resultado.next()) {
           /* np.add(resultado.getString("id"));
            np.add(resultado.getString("numerodecuenta"));
            np.add(resultado.getString("saldo"));
            np.add(resultado.getString("tipodecuenta"));*/
            np.add(resultado.getString("saldo"));
        }

        return np;
    }

    public static void registrarCuenta(Connection con , Cuenta cuenta) throws SQLException {

        con.setAutoCommit(true);
        PreparedStatement insertCuenta = null;
        //Toca generalizar la sentencia con ?

        String insertStatment = "INSERT INTO CUENTA VALUES (?,?,?,?)";

        insertCuenta = con.prepareStatement(insertStatment);

        System.out.println(cuenta.getUsuario());

        insertCuenta.setString(1, cuenta.getNumerodecuenta());
        insertCuenta.setFloat(2, cuenta.getSaldo());
        insertCuenta.setString(3, cuenta.getTipodecuenta());
        insertCuenta.setString(4,cuenta.getUsuario());

        insertCuenta.execute();
        con.setAutoCommit(false);
        con.commit();

    }

    public static void actualizarSaldo(Connection con , String numerodecuenta, float saldo) throws SQLException {

        con.setAutoCommit(true);
        PreparedStatement updateCuenta = null;

        //Toca generalizar la sentencia con ?

        String updateStatment = "UPDATE CUENTA SET saldo=saldo+? where numerodecuenta=?";

        updateCuenta = con.prepareStatement(updateStatment);


        updateCuenta.setFloat(1, saldo);
        updateCuenta.setString(2, numerodecuenta);
        updateCuenta.execute();
        con.setAutoCommit(false);
        con.commit();
    }



    public static List<String> verUltimosMov(Connection con, String cuenta) throws SQLException {

        List<String> np=new LinkedList<>();

        PreparedStatement getmovs = null;
        System.out.println(cuenta);
        String consultarmov = "SELECT detalle,fecha,saldoatransferir,aprobacion FROM TRANSACCION where origen='"+cuenta+"'"+ "or destinatario='"+cuenta+"'"+" order by fecha DESC";
        getmovs = con.prepareStatement(consultarmov);

        ResultSet resultado = getmovs.executeQuery();
        while(resultado.next()) {
            np.add(resultado.getString("detalle"));
            np.add(resultado.getString("fecha"));
            np.add(resultado.getString("saldoatransferir"));
            np.add(resultado.getString("aprobacion"));

        }
        return np;
    }

    public static Set<Map<String,String>> getCuentas(Connection dbcon, String user) throws SQLException {


        if (UserServices.isAdmin(dbcon,user)) {
            PreparedStatement getCuentas = null;
            String consultaCuentas = "SELECT * FROM cuenta";
            getCuentas = dbcon.prepareStatement(consultaCuentas);
            ResultSet resultado = getCuentas.executeQuery();
            Set<Map<String,String>> jsongrande = new HashSet<>();
            ResultSetMetaData rsmd = resultado.getMetaData();
            while (resultado.next()) {
                Map<String, String> jsonpequeño = new HashMap<>();
                jsonpequeño.put(rsmd.getColumnName(1), resultado.getString("numerodecuenta"));
                jsonpequeño.put(rsmd.getColumnName(2), resultado.getString("saldo"));
                jsonpequeño.put(rsmd.getColumnName(3), resultado.getString("tipodecuenta"));
                jsonpequeño.put(rsmd.getColumnName(4), resultado.getString("usuario"));
                jsongrande.add(jsonpequeño);
            }
            return jsongrande;
        } else {
            return null;
        }
    }
}
