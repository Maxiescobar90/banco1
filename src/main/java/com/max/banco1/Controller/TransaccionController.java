package com.max.banco1.Controller;

import com.max.banco1.DTO.TransaccionDTO;
import com.max.banco1.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    private final TransaccionService transaccionService;

    @Autowired
    public TransaccionController(TransaccionService transaccionService){
        this.transaccionService = transaccionService;
    }


    @PostMapping("/ingreso/cuentaAhorro")
    public ResponseEntity<TransaccionDTO> realizarIngresoCuentaAhorro(@RequestParam Long cuentaAhorroId, @RequestParam double monto){

        TransaccionDTO ingreso = transaccionService.realizarIngresoCuentaAhorro(cuentaAhorroId, monto);
            if(ingreso != null){
                return new ResponseEntity<>(ingreso, HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }


    @PostMapping("/ingreso/cuentaCorriente")
    public ResponseEntity<TransaccionDTO> realizarIngresoCuentaCorriente(@RequestParam Long cuentaCorrienteId, @RequestParam double monto){

        TransaccionDTO ingreso = transaccionService.realizarIngresoCuentaCorriente(cuentaCorrienteId, monto);
        if (ingreso != null) {
            return new ResponseEntity<>(ingreso, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/extraccion/cuentaAhorro")
    public ResponseEntity<TransaccionDTO> realizarExtraccionCuentaAhorro(@RequestParam Long cuentaAhorroId, @RequestParam double monto){
        TransaccionDTO extraccion = transaccionService.realizarExtraccionCuentaAhorro(cuentaAhorroId, monto);

        if(extraccion != null){
            return new ResponseEntity<>(extraccion, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/extraccion/cuentaCorriente")
    public ResponseEntity<TransaccionDTO> realizarExtraccionCuentaCorriente(@RequestParam Long cuentaCorrienteId, @RequestParam double monto){
        TransaccionDTO extraccion = transaccionService.realizarExtraccionCuentaCorriente(cuentaCorrienteId, monto);

        if(extraccion != null){
            return new ResponseEntity<>(extraccion, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/transferencia")
    public ResponseEntity<TransaccionDTO> realizarTransferencia(
            @RequestParam Long numeroCuentaOrigen,
            @RequestParam Long numeroCuentaDestino,
            @RequestParam double monto) {

        TransaccionDTO transferencia = transaccionService.realizarTransferenciaPorId(numeroCuentaOrigen, numeroCuentaDestino, monto);

        if (transferencia != null) {
            return new ResponseEntity<>(transferencia, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
