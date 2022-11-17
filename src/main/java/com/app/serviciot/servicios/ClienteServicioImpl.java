
package com.app.serviciot.servicios;

import com.app.serviciot.entidades.Cliente;
import com.app.serviciot.repositorio.ClienteRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ClienteServicioImpl implements IClienteServicio{

    @Autowired
    private ClienteRepositorio repositorio; 
    
    @Override
    public List<Cliente> listarTodosLosClientes() {
      return repositorio.findAll();
    }

    @Override
    public void guardarCliente(Cliente cli) {
        repositorio.save(cli);
    }

    @Override
    public Optional<Cliente> buscarPorIdCliente(Long id) {
        return repositorio.findById(id);
    }
    
}
