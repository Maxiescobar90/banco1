package com.max.banco1.repositories;

import com.max.banco1.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Usuario save(Usuario usuario);

    void deleteById(Long id);
}
