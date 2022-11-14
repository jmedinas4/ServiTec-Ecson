
package com.app.serviciot.servicios;


import com.app.serviciot.entidades.Tecnico;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface ITecnicoServicio  {
    
    public List<Tecnico> listarTodosLosTecnicos();
    
    public Optional<Tecnico> findById(Long id);
    
    public HashMap<Optional<Tecnico>, String> buscarOptimo();
    
    
}
