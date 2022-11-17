
package com.app.serviciot.servicios;


import com.app.serviciot.entidades.Falla;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface IFallaServicio  {
    
    public List<Falla> listarTodosLosFallas();
    
    public Optional<Falla> buscarFallaPorID(Long id);

    public void guardarFalla(Falla falla);
    
    
}
