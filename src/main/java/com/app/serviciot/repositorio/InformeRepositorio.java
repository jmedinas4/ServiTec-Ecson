
package com.app.serviciot.repositorio;


import com.app.serviciot.entidades.Informe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InformeRepositorio extends JpaRepository<Informe, Long>{
    
}
