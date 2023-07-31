package com.max.banco1.Controller;

import com.max.banco1.DTO.CuentaDTO;
import com.max.banco1.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @DeleteMapping("/{cuentaId}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long cuentaId) {
        cuentaService.eliminarCuenta(cuentaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> obtenerCuentaPorId(@PathVariable Long id) {
        CuentaDTO cuentaDTO = cuentaService.getCuentaById(id);
        if (cuentaDTO != null) {
            return new ResponseEntity<>(cuentaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
