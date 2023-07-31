package com.max.banco1.service;

import com.max.banco1.DTO.TransaccionDTO;

public interface TransaccionService {

    TransaccionDTO realizarIngresoCuentaAhorro(Long cuentaAhorroId, double monto);

    TransaccionDTO realizarIngresoCuentaCorriente(Long cuentaCorrienteId, double monto);

    TransaccionDTO realizarExtraccionCuentaAhorro(Long cuentaAhorroId, double monto);

    TransaccionDTO realizarExtraccionCuentaCorriente(Long cuentaCorrienteId, double monto);
    TransaccionDTO realizarTransferenciaPorId(Long cuentaOrigenId, Long cuentaDestinoId, double monto);
}
