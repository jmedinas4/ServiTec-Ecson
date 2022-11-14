
package com.app.serviciot.servicios;


import com.app.serviciot.entidades.Tecnico;
import com.app.serviciot.repositorio.TecnicoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TecnicoServicioImpl implements ITecnicoServicio{

    @Autowired
    private TecnicoRepositorio repositorio; 
    
    
    @Override
    public List<Tecnico> listarTodosLosTecnicos() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Tecnico> findById(Long id) {
       return repositorio.findById(id);
    }
    
}
