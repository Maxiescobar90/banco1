package com.max.banco1.service;

import com.max.banco1.DTO.TransaccionDTO;
import com.max.banco1.entities.Cuenta;
import com.max.banco1.entities.CuentaAhorro;
import com.max.banco1.entities.CuentaCorriente;
import com.max.banco1.entities.Transaccion;
import com.max.banco1.repositories.CuentaAhorroRepository;
import com.max.banco1.repositories.CuentaCorrienteRepository;
import com.max.banco1.repositories.CuentaRepository;
import com.max.banco1.repositories.TransaccionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransaccionServiceImpl implements TransaccionService{

    private final TransaccionRepository transaccionRepository;
    private final CuentaCorrienteRepository cuentaCorrienteRepository;
    private final CuentaAhorroRepository cuentaAhorroRepository;

    private final CuentaRepository cuentaRepository;
    private final CuentaService cuentaService;
    private final ModelMapper modelMapper;

    @Autowired
    public TransaccionServiceImpl(TransaccionRepository transaccionRepository,
                                  CuentaAhorroRepository cuentaAhorroRepository,
                                  CuentaCorrienteRepository cuentaCorrienteRepository,
                                  ModelMapper modelMapper, CuentaService cuentaService, CuentaRepository cuentaRepository) {
        this.transaccionRepository = transaccionRepository;
        this.cuentaAhorroRepository = cuentaAhorroRepository;
        this.cuentaCorrienteRepository = cuentaCorrienteRepository;
        this.modelMapper = modelMapper;
        this.cuentaService = cuentaService;
        this.cuentaRepository = cuentaRepository;
    }


    @Override
    public TransaccionDTO realizarIngresoCuentaAhorro(Long cuentaAhorroId, double monto) {
        CuentaAhorro cuentaAhorro = cuentaAhorroRepository.findById(cuentaAhorroId)
                .orElse(null);

        if (cuentaAhorro == null) {
            return null; // Cuenta de ahorro no encontrada, puedes lanzar una excepción en lugar de retornar null
        }

        Transaccion transaccion = new Transaccion();
        transaccion.setTipo("INGRESO");
        transaccion.setMonto(monto);
        transaccion.setCuentaAhorro(cuentaAhorro);


        cuentaAhorro.setSaldo(cuentaAhorro.getSaldo() + monto);
        cuentaAhorroRepository.save(cuentaAhorro);

        return modelMapper.map(transaccionRepository.save(transaccion), TransaccionDTO.class);
    }

    @Override
    public TransaccionDTO realizarIngresoCuentaCorriente(Long cuentaCorrienteId, double monto) {
        CuentaCorriente cuentaCorriente = cuentaCorrienteRepository.findById(cuentaCorrienteId)
                .orElse(null);

        if (cuentaCorriente == null) {
            return null; // Cuenta corriente no encontrada, puedes lanzar una excepción en lugar de retornar null
        }

        Transaccion transaccion = new Transaccion();
        transaccion.setTipo("INGRESO");
        transaccion.setMonto(monto);
        transaccion.setCuentaCorriente(cuentaCorriente);


        cuentaCorriente.setSaldo(cuentaCorriente.getSaldo() + monto);
        cuentaCorrienteRepository.save(cuentaCorriente);

        return modelMapper.map(transaccionRepository.save(transaccion), TransaccionDTO.class);
    }

    @Override
    public TransaccionDTO realizarExtraccionCuentaAhorro(Long cuentaAhorroId, double monto) {
        CuentaAhorro cuentaAhorro = cuentaAhorroRepository.findById(cuentaAhorroId)
                .orElse(null);

        if (cuentaAhorro == null) {
            return null; // Cuenta de ahorro no encontrada, puedes lanzar una excepción en lugar de retornar null
        }

        if (cuentaAhorro.getSaldo() < monto) {
            return null; // Saldo insuficiente, puedes lanzar una excepción en lugar de retornar null
        }

        Transaccion transaccion = new Transaccion();
        transaccion.setTipo("EGRESO");
        transaccion.setMonto(monto);
        transaccion.setCuentaAhorro(cuentaAhorro);

        cuentaAhorro.setSaldo(cuentaAhorro.getSaldo() - monto);
        cuentaAhorroRepository.save(cuentaAhorro);

        return modelMapper.map(transaccionRepository.save(transaccion), TransaccionDTO.class);
    }

    @Override
    public TransaccionDTO realizarExtraccionCuentaCorriente(Long cuentaCorrienteId, double monto) {
        CuentaCorriente cuentaCorriente = cuentaCorrienteRepository.findById(cuentaCorrienteId)
                .orElse(null);

        if (cuentaCorriente == null) {
            return null; // Cuenta corriente no encontrada, puedes lanzar una excepción en lugar de retornar null
        }

        if (cuentaCorriente.getSaldo() < monto) {
            return null; // Saldo insuficiente, puedes lanzar una excepción en lugar de retornar null
        }

        Transaccion transaccion = new Transaccion();
        transaccion.setTipo("EGRESO");
        transaccion.setMonto(monto);
        transaccion.setCuentaCorriente(cuentaCorriente);

        cuentaCorriente.setSaldo(cuentaCorriente.getSaldo() - monto);
        cuentaCorrienteRepository.save(cuentaCorriente);

        return modelMapper.map(transaccionRepository.save(transaccion), TransaccionDTO.class);
    }



    @Override
    public TransaccionDTO realizarTransferenciaPorId(Long cuentaOrigenId, Long cuentaDestinoId, double monto) {
        Cuenta cuentaOrigen = cuentaRepository.findById(cuentaOrigenId).orElse(null);
        Cuenta cuentaDestino = cuentaRepository.findById(cuentaDestinoId).orElse(null);

        if (cuentaOrigen == null || cuentaDestino == null) {
            // Alguna de las cuentas no existe, no se puede realizar la transferencia
            return null;
        }

        if (cuentaOrigen.getSaldo() < monto) {
            // La cuenta de origen no tiene suficiente saldo para la transferencia
            return null;
        }

        // Realizar la transferencia
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);

        // Guardar las cuentas actualizadas en la base de datos
        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);

        // Crear una transacción para registrar la transferencia
        Transaccion transaccion = new Transaccion();
        transaccion.setTipo("TRANSFERENCIA");
        // Establecer otros datos de la transacción...
        transaccion = transaccionRepository.save(transaccion);

        return modelMapper.map(transaccion, TransaccionDTO.class);
    }



}
