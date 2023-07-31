package com.max.banco1.repositories;

import com.max.banco1.entities.CuentaAhorro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaAhorroRepository extends JpaRepository<CuentaAhorro, Long> {

}
