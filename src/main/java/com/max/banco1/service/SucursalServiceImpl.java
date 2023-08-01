package com.max.banco1.service;

import com.max.banco1.DTO.SucursalDTO;
import com.max.banco1.entities.Sucursal;
import com.max.banco1.repositories.SucursalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SucursalServiceImpl(SucursalRepository sucursalRepository, ModelMapper modelMapper) {
        this.sucursalRepository = sucursalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursalDTO) {
        Sucursal nuevaSucursal = modelMapper.map(sucursalDTO, Sucursal.class);
        nuevaSucursal = sucursalRepository.save(nuevaSucursal);

        return modelMapper.map(nuevaSucursal, SucursalDTO.class);
    }
}
