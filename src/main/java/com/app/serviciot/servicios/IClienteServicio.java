
package com.app.serviciot.servicios;

import com.app.serviciot.entidades.Cliente;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface IClienteServicio  {
    
    public List<Cliente> listarTodosLosClientes();
    
    public void guardarCliente(Cliente cli);
}
