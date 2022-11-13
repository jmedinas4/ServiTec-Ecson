
package com.app.serviciot.controladores;


import com.app.serviciot.servicios.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller

public class AplicacionController {
    @Autowired
    private IClienteServicio servicio; 
    
    
    @GetMapping("/")
    public String pagInicio(){
        return "index";
    }
    
    @GetMapping("/estudiantes")
    public String listarClientes(Model modelo){
        modelo.addAttribute("clientes",  servicio.listarTodosLosEstudiantes());
        return "index";
    }
    
}
