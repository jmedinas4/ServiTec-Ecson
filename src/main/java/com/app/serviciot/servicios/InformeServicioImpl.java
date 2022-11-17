
package com.app.serviciot.servicios;

import com.app.serviciot.entidades.Cliente;
import com.app.serviciot.entidades.Informe;
import com.app.serviciot.repositorio.ClienteRepositorio;
import com.app.serviciot.repositorio.InformeRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class InformeServicioImpl implements IInformeServicio{

    @Autowired
    private InformeRepositorio repositorio; 

    @Override
    public List<Informe> listarTodosLosInformes() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Informe> buscarPorIdInforme(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public void guardarInforme(Informe inmfor) {
        repositorio.save(inmfor);
    }
    
   
    
}
