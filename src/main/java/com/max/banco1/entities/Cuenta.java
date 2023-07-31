package com.max.banco1.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCuenta;

    public double saldo;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;



    public String generarNumeroCuentaUnico() {
        // Generar un número de cuenta único utilizando el ID del cliente y un número aleatorio.
        // Aquí tienes un ejemplo de cómo se podría hacer:
        Random random = new Random();
        String numeroUnico = String.format("%04d", random.nextInt(10000)); // Genera un número aleatorio de 4 dígitos
        return "Cuenta-" + this.cliente.getId() + "-" + numeroUnico;
    }


}
