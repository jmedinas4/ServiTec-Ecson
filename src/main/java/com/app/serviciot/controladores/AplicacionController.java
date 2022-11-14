
package com.app.serviciot.controladores;


import com.app.serviciot.servicios.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller
public class AplicacionController {
    
    
    
    @GetMapping("/")
    public String pagInicio(){
        return "redirect:/cliente/reportar";
    }
    
   
    
}
