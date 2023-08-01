package com.max.banco1.service;

import com.max.banco1.DTO.ClienteDTO;
import com.max.banco1.DTO.CuentaAhorroDTO;
import com.max.banco1.entities.Cliente;
import com.max.banco1.entities.CuentaAhorro;
import com.max.banco1.entities.CuentaCorriente;
import com.max.banco1.entities.Sucursal;
import com.max.banco1.repositories.ClienteRepository;
import com.max.banco1.repositories.CuentaAhorroRepository;
import com.max.banco1.repositories.CuentaCorrienteRepository;
import com.max.banco1.repositories.SucursalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final CuentaAhorroRepository cuentaAhorroRepository;
    private final CuentaCorrienteRepository cuentaCorrienteRepository;

    private final SucursalRepository sucursalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ModelMapper modelMapper, CuentaCorrienteRepository cuentaCorrienteRepository, CuentaAhorroRepository cuentaAhorroRepository, SucursalRepository sucursalRepository) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.cuentaCorrienteRepository = cuentaCorrienteRepository;
        this.cuentaAhorroRepository = cuentaAhorroRepository;
        this.sucursalRepository = sucursalRepository;
    }


    private String generarNumeroCuentaAhorroUnico(Long clienteId) {
        // Generar un número de cuenta único para cuentas de ahorro basado en el ID del cliente y un número aleatorio
        Random random = new Random();
        String numeroUnico = String.format("%04d", random.nextInt(10000)); // Genera un número aleatorio de 4 dígitos
        return "AH-" + clienteId + "-" + numeroUnico;
    }

    private String generarNumeroCuentaCorrienteUnico(Long clienteId) {
        // Generar un número de cuenta único para cuentas corrientes basado en el ID del cliente y un número aleatorio
        Random random = new Random();
        String numeroUnico = String.format("%04d", random.nextInt(10000)); // Genera un número aleatorio de 4 dígitos
        return "CC-" + clienteId + "-" + numeroUnico;
    }

    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        cliente = clienteRepository.save(cliente);

        // Crear cuenta de ahorro
        CuentaAhorro cuentaAhorro = new CuentaAhorro();
        cuentaAhorro.setNumeroCuenta(generarNumeroCuentaAhorroUnico(cliente.getId()));
        cuentaAhorro.setSaldo(0.0);
        cuentaAhorro.setCliente(cliente);
        cuentaAhorroRepository.save(cuentaAhorro);

        // Crear cuenta corriente
        CuentaCorriente cuentaCorriente = new CuentaCorriente();
        cuentaCorriente.setNumeroCuenta(generarNumeroCuentaCorrienteUnico(cliente.getId()));
        cuentaCorriente.setSaldo(0.0);
        cuentaCorriente.setCliente(cliente);
        cuentaCorrienteRepository.save(cuentaCorriente);

        return modelMapper.map(cliente, ClienteDTO.class);
    }

    @Override
    public Cliente actualizarCliente(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();


            cliente.setNombre(clienteDTO.getNombre());
            cliente.setApellido(clienteDTO.getApellido());
            cliente.setDni(clienteDTO.getDni());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setPassword(clienteDTO.getPassword());



            cliente = clienteRepository.save(cliente);
            return cliente;
        }

        return null;
    }

    @Override
    public ClienteDTO getClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null) {
            return null;
        }
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    @Override
    public boolean eliminarClientePorId(Long clienteId) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            clienteRepository.delete(cliente);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ClienteDTO> mostrarClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public ClienteDTO agregarCuentaAhorro(Long clienteId, CuentaAhorroDTO cuentaAhorroDTO) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if (cliente == null) {
            return null; // Cliente no encontrado, podrías lanzar una excepción en lugar de retornar null
        }
        CuentaAhorro cuentaAhorro = modelMapper.map(cuentaAhorroDTO, CuentaAhorro.class);
        cuentaAhorro.setCliente(cliente); // Asociar la cuenta al cliente
        cliente.getCuentas().add(cuentaAhorro); // Agregar la cuenta a la lista de cuentas del cliente
        cliente = clienteRepository.save(cliente); // Guardar el cliente actualizado

        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public Cliente asignarSucursalACliente(Long clienteId, Long sucursalId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        Sucursal sucursal = sucursalRepository.findById(sucursalId).orElse(null);

        if (cliente != null && sucursal != null) {
            cliente.setSucursal(sucursal);
            return clienteRepository.save(cliente);
        }

        return null; // Cliente o sucursal no encontrados
    }


}
