package com.max.banco1.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido;

    private Long dni;

    private String email;

    private String password;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Cuenta> cuentas;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL) // Nombre del atributo "cliente" en la clase CuentaAhorro
    private List<CuentaAhorro> cuentasAhorro;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL) // Nombre del atributo "cliente" en la clase CuentaCorriente
    private List<CuentaCorriente> cuentasCorriente;
}
