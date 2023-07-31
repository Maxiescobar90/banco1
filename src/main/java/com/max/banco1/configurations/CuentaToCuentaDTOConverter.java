package com.max.banco1.configurations;

import com.max.banco1.DTO.CuentaDTO;
import com.max.banco1.entities.Cuenta;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;

@Component
public class CuentaToCuentaDTOConverter extends AbstractConverter<Cuenta, CuentaDTO> {

    @Override
    protected CuentaDTO convert(Cuenta cuenta) {
        return CuentaDTO.builder()
                .id(cuenta.getId())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .saldo(cuenta.getSaldo())
                .build();
    }
}
