package com.app.serviciot.controladores;

import com.app.serviciot.entidades.Cliente;
import com.app.serviciot.entidades.Falla;
import com.app.serviciot.entidades.Tecnico;
import com.app.serviciot.servicios.IClienteServicio;
import com.app.serviciot.servicios.ITecnicoServicio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    private ITecnicoServicio servicioT;

    @GetMapping("/cliente/reportar")
    public String listarClientes(Model modelo) {
        Cliente miCliente = new Cliente();
        Falla miFalla = new Falla();
        List<Falla> fallas;
        modelo.addAttribute("clientes", servicio.listarTodosLosClientes());
        System.out.println("desde aca");
        for (Cliente listado : servicio.listarTodosLosClientes()) {
//            fallas= listado.getFallas();
            System.out.println("dir = " + listado.getDireccion());
            System.out.println("fallas = " + listado.getFallas());
            
//            for (Falla falla : fallas) {
//                System.out.println("hora");
////                System.out.println("falla = " + falla.());
//            }
            System.out.println("listado = " + listado.getNombre());
        }
        System.out.println("hasta aca");
        modelo.addAttribute("miCliente", miCliente);
        modelo.addAttribute("miFalla", miFalla);

        return "reportar";
    }

    @PostMapping("/cliente/reporte")
    public String guardarFalla(@ModelAttribute("miCliente") Cliente cliente, @ModelAttribute("miFalla") Falla falla) {
//         System.out.println(falla.getDescripcion());
//         
//         System.out.println("cliente.getNombre() = " + cliente.getNombre());
//        Tecnico tecnicooptimo = servicioT.buscarOptimo().get();
        Tecnico tecnicooptimo = null;
        String horarioOptimo = "";
        HashMap<Optional<Tecnico>, String> horariosDisponibles = servicioT.buscarOptimo();
        if (horariosDisponibles != null) {
            for (Optional<Tecnico> optional : horariosDisponibles.keySet()) {
                tecnicooptimo = optional.get();
                horarioOptimo = horariosDisponibles.get(optional);
                System.out.println("el tecnico optimo es:" + tecnicooptimo.getNombre());
                System.out.println("el horarioOptimo optimo es:" + horarioOptimo);
                
                Falla falla1 = new Falla(falla.getDescripcion(), cliente, tecnicooptimo, horarioOptimo);
                List<Falla> fallas= cliente.getFallas();
                if (fallas!=null) {
                    fallas.add(falla1);
                    cliente.setFallas(fallas);
                    servicio.guardarCliente(cliente);
                }else{
                    List<Falla> fallas2=new ArrayList<>();
                    fallas2.add(falla1);
                    cliente.setFallas(fallas2);
                    servicio.guardarCliente(cliente);
                }
                
                
            }
        }else{
            System.out.println("paila, no hay orario");
        }

        
        return "reportar";
    }
}
