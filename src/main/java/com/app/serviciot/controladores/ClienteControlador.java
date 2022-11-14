
package com.app.serviciot.controladores;

import com.app.serviciot.entidades.Cliente;
import com.app.serviciot.entidades.Falla;
import com.app.serviciot.servicios.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteControlador {
    
    @Autowired
    private IClienteServicio servicio; 
    
     @GetMapping("/cliente/reportar")
    public String listarClientes(Model modelo){
        Cliente miCliente = new Cliente();
        Falla miFalla = new Falla();
        modelo.addAttribute("clientes",  servicio.listarTodosLosClientes());
        modelo.addAttribute("miCliente",  miCliente);
        modelo.addAttribute("miFalla",  miFalla);
 
        return "reportar";
    }
    
    @PostMapping("/cliente/reportar")
    public String guardarFalla(@ModelAttribute("miCliente") Cliente  cliente, @ModelAttribute("miFalla") Falla  falla){
         System.out.println(falla.getDescripcion());
         System.out.println("cliente.getNombre() = " + cliente.getNombre());
        return "reportar";
    }
}
