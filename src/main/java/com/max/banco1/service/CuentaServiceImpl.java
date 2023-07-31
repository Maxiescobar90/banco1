package com.max.banco1.service;

import com.max.banco1.DTO.CuentaDTO;
import com.max.banco1.entities.Cuenta;
import com.max.banco1.repositories.CuentaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaServiceImpl implements CuentaService{
    private final CuentaRepository cuentaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CuentaServiceImpl(CuentaRepository cuentaRepository, ModelMapper modelMapper) {
        this.cuentaRepository = cuentaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CuentaDTO getCuentaById(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElse(null);
        if (cuenta == null) {
            return null;
        }
        return modelMapper.map(cuenta, CuentaDTO.class);
    }

    @Override
    public CuentaDTO crearCuenta(CuentaDTO cuentaDTO) {
        Cuenta cuenta = modelMapper.map(cuentaDTO, Cuenta.class);
        cuenta = cuentaRepository.save(cuenta);
        return modelMapper.map(cuenta, CuentaDTO.class);
    }

    @Override
    public CuentaDTO actualizarCuenta(Long id, CuentaDTO cuentaDTO) {
        Cuenta cuenta = cuentaRepository.findById(id).orElse(null);
        if (cuenta == null) {
            return null;
        }
        // Actualizar los atributos de la cuenta con los valores del DTO
        // Asegúrate de manejar las validaciones y lógica de negocio necesarias aquí
        // Por ejemplo:
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        // Actualizar otros atributos...
        cuenta = cuentaRepository.save(cuenta);
        return modelMapper.map(cuenta, CuentaDTO.class);
    }

    @Override
    public void eliminarCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }
}
