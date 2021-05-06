package edu.eci.mcsw.controller;

import edu.eci.mcsw.model.Credentials;
import edu.eci.mcsw.model.Usuario;
import edu.eci.mcsw.persistence.JDBC;
import edu.eci.mcsw.services.ServicesException;
import edu.eci.mcsw.services.TransaccionServices;
import edu.eci.mcsw.services.UserServices;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UserController {

    JDBC cliente = new JDBC();
    Connection dbcon = cliente.con;

    @PostMapping("ingreso")
    public ResponseEntity<?> login(@RequestBody Credentials credentials){

        try {
            List<String> login = UserServices.dologin(dbcon,credentials);
            if (login!=null) {
                return new ResponseEntity<>(login, HttpStatus.ACCEPTED);
            }
            throw new ServicesException(ServicesException.USUARIO_NO_REGISTRADO_EN_SISTEMA);

        } catch (SQLException | ServicesException e) {
            return new ResponseEntity<>("login failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("registro-sistema")
    public ResponseEntity<?> registrarUsuarioEnSistema(@RequestBody Usuario usuario){

        try {
            UserServices.registrarUsuarioSistema(dbcon,usuario);

            return new ResponseEntity<>("registro success", HttpStatus.ACCEPTED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("registro failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("registro-app")
    public ResponseEntity<?> registrarUsuarioApp(@RequestBody Usuario usuario){

        System.out.println(usuario);
        try {
            UserServices.registrarUsuarioApp(dbcon,usuario);

            return new ResponseEntity<>("Registro exitoso", HttpStatus.ACCEPTED);
        } catch (SQLException e) {
            return new ResponseEntity<>("Este usuario ya se ha registrado o ha ocurrido un error", HttpStatus.BAD_REQUEST);
        } catch (ServicesException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("Y3Jpc3RpYW4gZXMgZ2F5")
    public ResponseEntity<?> ingresocritico(@RequestBody Credentials usuario){

        try {
            UserServices.dologincritico(dbcon,usuario);
            return new ResponseEntity<>("login success", HttpStatus.ACCEPTED);
        } catch (SQLException e) {
            return new ResponseEntity<>("login failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> accounts(){

        try {

            return new ResponseEntity<>( UserServices.accounts(dbcon), HttpStatus.ACCEPTED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("get failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/cuenta")
    public ResponseEntity<?> getCuenta(@RequestParam String cedula){

        try {
            return new ResponseEntity<>( UserServices.getCuentaByUser(dbcon,cedula), HttpStatus.ACCEPTED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Cuenta get failed", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/transacciones")
    public ResponseEntity<?> getTransacciones(@RequestParam String usuario){

        try {
            return new ResponseEntity<>( TransaccionServices.getTransaccionById(dbcon,usuario), HttpStatus.ACCEPTED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("No se pudieron obtener las transacciones del usuario"+usuario, HttpStatus.BAD_REQUEST);
        }
    }


}
