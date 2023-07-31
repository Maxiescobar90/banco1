package com.max.banco1.service;

import com.max.banco1.DTO.CuentaDTO;

public interface CuentaService {

    CuentaDTO getCuentaById(Long id);
    CuentaDTO crearCuenta(CuentaDTO cuentaDTO);
    CuentaDTO actualizarCuenta(Long id, CuentaDTO cuentaDTO);

    void eliminarCuenta(Long id);
}
