
package com.app.serviciot.repositorio;

import com.app.serviciot.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{
    
}
