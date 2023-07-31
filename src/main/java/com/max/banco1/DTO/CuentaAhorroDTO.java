package com.max.banco1.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaAhorroDTO extends CuentaDTO{

    private double saldo;
}
