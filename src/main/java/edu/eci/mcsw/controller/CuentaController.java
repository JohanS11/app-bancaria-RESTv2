package edu.eci.mcsw.controller;

import edu.eci.mcsw.model.Cuenta;
import edu.eci.mcsw.persistence.JDBC;
import edu.eci.mcsw.services.CuentaServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
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
            return new ResponseEntity<>("Consulta failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("all")
    public ResponseEntity<?> verCuentas(@RequestParam String userquery){
        try {
            Set<Map<String, String>> obj = CuentaServices.getCuentas(dbcon,userquery);
            if (obj == null){ return new ResponseEntity<>("Not authorized to perform this operation", HttpStatus.FORBIDDEN);}
            return new ResponseEntity<>(obj, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Not authorized to perform this operation", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{cuenta}")
    public ResponseEntity<?> modificarSaldo(@PathVariable("cuenta") String cuenta, float saldonuevo){
        try {
            CuentaServices.actualizarSaldo(dbcon,cuenta,saldonuevo);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (SQLException throwables) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}
