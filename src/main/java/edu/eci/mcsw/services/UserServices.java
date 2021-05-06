package edu.eci.mcsw.services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.eci.mcsw.model.Credentials;
import edu.eci.mcsw.model.Cuenta;
import edu.eci.mcsw.model.Usuario;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.*;

public class UserServices {


    public static void registrarUsuarioSistema(Connection con, Usuario usuario) throws SQLException {

        con.setAutoCommit(true);
        PreparedStatement insertUsuario = null;

        PreparedStatement selectUsuariosSistema = null;

        //Toca generalizar la sentencia con ?


        String insertStatment = "INSERT INTO USUARIO_SISTEMA VALUES (?,?,?,?,?,?,?,?)";

        insertUsuario = con.prepareStatement(insertStatment);

        insertUsuario.setString(1, usuario.getApellido());
        insertUsuario.setString(2, usuario.getNombre());
        insertUsuario.setString(3, usuario.getCelular());
        insertUsuario.setString(4, usuario.getCorreo());
        insertUsuario.setString(5, usuario.getCedula());
        insertUsuario.setString(6, usuario.getUsuarioid());
        insertUsuario.setString(7, usuario.getPwd());
        insertUsuario.setString(8, usuario.getRol().name());
        insertUsuario.execute();
        con.setAutoCommit(false);

        con.commit();

    }

    public static List<String> dologin(Connection con, Credentials credentials) throws SQLException {

        List<String> np = new LinkedList<>();

        PreparedStatement getuser = null;
        String consultaUsuarios = "SELECT correo,nombre,cedula FROM USUARIO where correo=" + "'" + credentials.getEmail() + "'" + "and pwd=" + "'" + credentials.getPassword() + "'";
        getuser = con.prepareStatement(consultaUsuarios);
        ResultSet resultado = getuser.executeQuery();
        while (resultado.next()) {
            np.add(resultado.getString("correo"));
            np.add(resultado.getString("nombre"));
            np.add(resultado.getString("cedula"));
        }
        if (np.size() != 0) {
            return np;
        }
        return null;

    }

    public static Set<Map<String,String>> getCuentaByUser(Connection con, String cedula) throws SQLException {

        PreparedStatement getCuenta = null;
        String consultaUsuarios = "SELECT * FROM cuenta where usuario='" + cedula + "'";
        getCuenta = con.prepareStatement(consultaUsuarios);
        ResultSet resultado = getCuenta.executeQuery();
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
    }

    public static List<String> accounts(Connection con) throws SQLException {

        List<String> np=new LinkedList<>();

        PreparedStatement getuser = null;
        String consultaUsuarios = "SELECT * FROM CUENTA";
        getuser = con.prepareStatement(consultaUsuarios);

        ResultSet resultado = getuser.executeQuery();
        while(resultado.next()) {
            np.add(resultado.getString("numerodecuenta"));
            np.add(resultado.getString("saldo"));
            np.add(resultado.getString("tipodecuenta"));
            np.add(resultado.getString("usuario"));
        }
        return np;

    }

    public static boolean dologincritico(Connection con, Credentials credentials) throws SQLException {

        List<String> np=new LinkedList<>();

        PreparedStatement getuser = null;
        String consultaUsuarios = "SELECT correo FROM USUARIO_ADMIN where correo="+"'"+credentials.getEmail()+"'"+"and pwd="+"'"+credentials.getPassword()+"'";
        getuser = con.prepareStatement(consultaUsuarios);
        ResultSet resultado = getuser.executeQuery();
        while(resultado.next()) {
            np.add(resultado.getString("correo"));
        }
        System.out.println(np);

        return np.size()==0?false:true;

    }

    public static void registrarUsuarioApp(Connection con , Usuario usuario) throws SQLException, ServicesException {

        con.setAutoCommit(true);

        PreparedStatement insertUsuario = null;

        PreparedStatement selectUsuario = null;

        String selectStatment = "SELECT cedula FROM USUARIO_SISTEMA WHERE cedula='"+usuario.getCedula()+"'";

        selectUsuario = con.prepareStatement(selectStatment);
        ResultSet resultado = selectUsuario.executeQuery();
        con.setAutoCommit(false);

        if (resultado.next()){
            String insertStatment = "INSERT INTO USUARIO VALUES (?,?,?,?,?,?,?,?)";

            insertUsuario = con.prepareStatement(insertStatment);

            insertUsuario.setString(1, usuario.getApellido());
            insertUsuario.setString(2, usuario.getNombre());
            insertUsuario.setString(3, usuario.getCelular());
            insertUsuario.setString(4, usuario.getCorreo());
            insertUsuario.setString(5, usuario.getCedula());
            insertUsuario.setString(6, usuario.getUsuarioid());
            insertUsuario.setString(7, usuario.getPwd());
            insertUsuario.setString(8, usuario.getRol().name());
            insertUsuario.execute();
        } else{
            throw new ServicesException(ServicesException.USUARIO_NO_REGISTRADO_EN_SISTEMA);
        }

        con.commit();

    }

}

