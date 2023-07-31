package com.max.banco1.repositories;

import com.max.banco1.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    Cliente save(Cliente cliente);

    void deleteById(Long id);
}
