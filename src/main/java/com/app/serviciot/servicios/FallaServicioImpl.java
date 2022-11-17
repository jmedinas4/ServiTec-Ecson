package com.app.serviciot.servicios;

import com.app.serviciot.entidades.Falla;
import com.app.serviciot.entidades.Tecnico;
import com.app.serviciot.repositorio.FallaRepositorio;
import com.app.serviciot.repositorio.TecnicoRepositorio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class FallaServicioImpl implements IFallaServicio {

    @Autowired
    private FallaRepositorio repositorio;

  

    @Override
    public List<Falla> listarTodosLosFallas() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Falla> buscarFallaPorID(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public void guardarFalla(Falla falla) {
        repositorio.save(falla);
    }
    

    
}
