package com.max.banco1.service;

import com.max.banco1.DTO.ClienteDTO;
import com.max.banco1.DTO.CuentaAhorroDTO;
import com.max.banco1.entities.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente actualizarCliente(Long id, ClienteDTO clienteDTO);

    ClienteDTO getClienteById(Long id);

    ClienteDTO crearCliente(ClienteDTO clienteDTO);

    boolean eliminarClientePorId(Long clienteId);

    List<ClienteDTO> mostrarClientes();

    ClienteDTO agregarCuentaAhorro(Long clienteId, CuentaAhorroDTO cuentaAhorroDTO);

}
