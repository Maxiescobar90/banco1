package com.max.banco1.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CuentaDTO {

    private Long id;
    private String numeroCuenta;
    private double saldo;
}
