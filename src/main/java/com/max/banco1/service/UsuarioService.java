package com.max.banco1.service;

import com.max.banco1.DTO.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO getUsuarioById(Long id);
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO);
    void eliminarUsuario(Long id);
}
