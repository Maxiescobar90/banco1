package com.max.banco1.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ClienteDTO {

    @Id
    private Long id;

    @NotEmpty
    @Size(min = 4,message = "El nombre de usuario debe tener un mínimo de 4 caracteres")
    private String nombre;

    @NotEmpty
    @Size(min = 4,message = "El apellido de usuario debe tener un mínimo de 4 caracteres")
    private String apellido;

    private Long dni;

    @Email(message = "La dirección de correo electrónico es inválida !!")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10,message = "La contraseña debe tener un mínimo de 3 caracteres y un máximo de 10 caracteres !!")
    private String password;
}
