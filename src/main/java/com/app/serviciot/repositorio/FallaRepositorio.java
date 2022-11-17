
package com.app.serviciot.repositorio;


import com.app.serviciot.entidades.Falla;
import com.app.serviciot.entidades.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FallaRepositorio extends JpaRepository<Falla, Long>{
    
}
