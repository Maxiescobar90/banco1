package com.max.banco1.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaCorrienteDTO extends CuentaDTO{

    private double saldo;
}
