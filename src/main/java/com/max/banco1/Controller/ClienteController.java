package com.max.banco1.Controller;

import com.max.banco1.DTO.ClienteDTO;
import com.max.banco1.DTO.CuentaAhorroDTO;
import com.max.banco1.entities.Cliente;
import com.max.banco1.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    private final ModelMapper modelMapper;

    @Autowired
    public ClienteController(ClienteService clienteService, ModelMapper modelMapper) {
        this.clienteService = clienteService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/crearCliente")
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO nuevoCliente = clienteService.crearCliente(clienteDTO);
        if (nuevoCliente != null) {
            return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{clienteId}/cuentas/ahorro")
    public ResponseEntity<ClienteDTO> agregarCuentaAhorro(@PathVariable Long clienteId, @RequestBody CuentaAhorroDTO cuentaAhorroDTO) {
        ClienteDTO clienteActualizado = clienteService.agregarCuentaAhorro(clienteId, cuentaAhorroDTO);
        if (clienteActualizado != null) {
            return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerCliente(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.getClienteById(id);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<String> eliminarClientePorId(@PathVariable Long clienteId) {
        boolean eliminado = clienteService.eliminarClientePorId(clienteId);
        if (eliminado) {
            return new ResponseEntity<>("Cliente eliminado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/mostrarClientes")
    public ResponseEntity<List<ClienteDTO>> obtenerTodosLosClientes() {
        List<ClienteDTO> clientes = clienteService.mostrarClientes();
        if (!clientes.isEmpty()) {
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Cliente clienteActualizado = clienteService.actualizarCliente(id, clienteDTO);

        if (clienteActualizado != null) {
            ClienteDTO clienteDTOActualizado = modelMapper.map(clienteActualizado, ClienteDTO.class);
            return new ResponseEntity<>(clienteDTOActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
