package com.max.banco1.Controller;

import com.max.banco1.DTO.SucursalDTO;
import com.max.banco1.entities.Sucursal;
import com.max.banco1.repositories.SucursalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    private final SucursalRepository sucursalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SucursalController(SucursalRepository sucursalRepository, ModelMapper modelMapper) {
        this.sucursalRepository = sucursalRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/crearSucursal")
    public ResponseEntity<SucursalDTO> crearSucursal(@RequestBody SucursalDTO sucursalDTO) {

        Sucursal nuevaSucursal = modelMapper.map(sucursalDTO, Sucursal.class);

        nuevaSucursal = sucursalRepository.save(nuevaSucursal);

        SucursalDTO sucursalCreadaDTO = modelMapper.map(nuevaSucursal, SucursalDTO.class);
        return new ResponseEntity<>(sucursalCreadaDTO, HttpStatus.CREATED);
    }
}
