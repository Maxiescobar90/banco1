package com.max.banco1.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class TransaccionDTO {

    private Long id;
    private String tipo;
    private Long cuentaId; // Para indicar el ID de la cuenta relacionada
    private double monto;
}
