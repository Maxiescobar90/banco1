package com.max.banco1.repositories;

import com.max.banco1.DTO.CuentaDTO;
import com.max.banco1.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Cuenta findById(long id);


}
