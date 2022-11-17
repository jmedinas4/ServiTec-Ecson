
package com.app.serviciot.servicios;

import com.app.serviciot.entidades.Cliente;
import com.app.serviciot.entidades.Informe;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface IInformeServicio  {
    
    public List<Informe> listarTodosLosInformes();
    
    public Optional<Informe> buscarPorIdInforme(Long id);
    
    public void guardarInforme(Informe cli);
}
