
package com.app.serviciot.servicios;

import com.app.serviciot.entidades.Cliente;
import com.app.serviciot.repositorio.ClienteRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ClienteServicioImpl implements IClienteServicio{

    @Autowired
    private ClienteRepositorio repositorio; 
    
    @Override
    public List<Cliente> listarTodosLosEstudiantes() {
      return repositorio.findAll();
    }
    
}
