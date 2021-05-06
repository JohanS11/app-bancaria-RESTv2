package edu.eci.mcsw.controller;

import edu.eci.mcsw.model.Cuenta;
import edu.eci.mcsw.persistence.JDBC;
import edu.eci.mcsw.services.CuentaServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/cuentas")
@CrossOrigin("*")
public class CuentaController {

    JDBC cliente = new JDBC();
    Connection dbcon = cliente.con;

    @PostMapping("registro")
    public ResponseEntity<?> registrarCuenta(@RequestBody Cuenta cuenta){

        try {
            CuentaServices.registrarCuenta(dbcon,cuenta);
            return new ResponseEntity<>("cuenta success", HttpStatus.ACCEPTED);
        } catch (SQLException e) {
            return new ResponseEntity<>("cuenta failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{cuenta}/movimientos")
    public ResponseEntity<?> verUltimosMovimiento(@PathVariable String cuenta){

        try {

            return new ResponseEntity<>(CuentaServices.verUltimosMov(dbcon,cuenta), HttpStatus.ACCEPTED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Consulta failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{cuenta}/dinero")
    public ResponseEntity<?> verDineroDisponible(@PathVariable String cuenta){

        try {

            return new ResponseEntity<>(CuentaServices.verCuenta(dbcon,cuenta), HttpStatus.ACCEPTED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Consulta failed", HttpStatus.BAD_REQUEST);
        }
    }


}
