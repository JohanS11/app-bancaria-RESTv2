package edu.eci.mcsw.controller;

import edu.eci.mcsw.model.Transaccion;
import edu.eci.mcsw.persistence.JDBC;
import edu.eci.mcsw.services.TransaccionServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/transacciones")
@CrossOrigin("*")
public class TransaccionController {

    JDBC cliente = new JDBC();
    Connection dbcon = cliente.con;

    @PostMapping("registro")
    public ResponseEntity<?> registrarTransaccion(@RequestBody Transaccion transaccion){

        try {
            TransaccionServices.registrarTransaccion(dbcon,transaccion);
            return new ResponseEntity<>("transaccion success", HttpStatus.ACCEPTED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("transaccion failed", HttpStatus.BAD_REQUEST);
        }
    }
}
