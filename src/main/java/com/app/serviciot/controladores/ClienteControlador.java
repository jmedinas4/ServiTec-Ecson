package com.app.serviciot.controladores;

import com.app.serviciot.entidades.Cliente;
import com.app.serviciot.entidades.Falla;
import com.app.serviciot.entidades.Tecnico;
import com.app.serviciot.servicios.IClienteServicio;
import com.app.serviciot.servicios.IFallaServicio;
import com.app.serviciot.servicios.ITecnicoServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ClienteControlador {

    @Autowired
    private IClienteServicio servicio;
    @Autowired
    private ITecnicoServicio servicioT;
    
    @Autowired
    private IFallaServicio servicioF;

    @GetMapping("/cliente/reportar")
    public String listarClientes(Model modelo) {
        Cliente miCliente = new Cliente();
        Falla miFalla = new Falla();
        List<Falla> fallas;
        modelo.addAttribute("clientes", servicio.listarTodosLosClientes());
        
        modelo.addAttribute("miCliente", miCliente);
        modelo.addAttribute("miFalla", miFalla);

        return "reportar";
    }
    
    @GetMapping("/probar/cliente")
    public String pruebaGuardar(Model model){
        Cliente cli = servicio.buscarPorIdCliente(1L).get();
        Tecnico tec = servicioT.findById(1L).get();
       Falla falla = new Falla("hola", cli,tec, "10:00:00");
        servicioF.guardarFalla(falla);
        return "index";
    }
    
    
    
    @GetMapping("/cliente/reportok/{parametro}")
    public String mostrarOk(@PathVariable("parametro") String parametro,Model model){
        String[] resultados = parametro.split(",");
        model.addAttribute("tecnico", resultados[0] );
        model.addAttribute("horario", resultados[1] );
        model.addAttribute("nombre", resultados[2] );
        
        return "reportok";
    }
     @GetMapping("/cliente/reportmal/{parametro}")
    public String mostrarMal(@PathVariable("parametro") String parametro,Model model){
       
        model.addAttribute("resultado", parametro );
        
        return "reportmal";
    }
    
    @PostMapping("/cliente/reporte")
    public String guardarFalla(@ModelAttribute("miCliente") Cliente cliente, @ModelAttribute("miFalla") Falla falla) {
//        
        Tecnico tecnicooptimo = null;
        String horarioOptimo = "";
       
        HashMap<Optional<Tecnico>, String> horariosDisponibles = servicioT.buscarOptimo();
        
        if (horariosDisponibles != null) {
            for (Optional<Tecnico> optional : horariosDisponibles.keySet()) {
                tecnicooptimo = optional.get();
                
                servicio.guardarCliente(cliente);
                long clientes;
                horarioOptimo = horariosDisponibles.get(optional);
                System.out.println("la lista esta vacia? = " + servicio.listarTodosLosClientes().isEmpty());
                if (servicio.listarTodosLosClientes().isEmpty()) {
                    clientes=1L;
                }else{
                    servicio.listarTodosLosClientes().forEach((t) -> {
                        System.out.println("t = " + t.getId());
                    });
                    int posicion = servicio.listarTodosLosClientes().size();
                    System.out.println("tamaño lista"+ servicio.listarTodosLosClientes().size());
                    System.out.println("ultimo ud"+ servicio.listarTodosLosClientes().get(posicion-1).getId());
//                    System.out.println("resultadoamostrar = " + servicio.listarTodosLosClientes().get(servicio.listarTodosLosClientes().size()).getId());
                    clientes = servicio.listarTodosLosClientes().get(servicio.listarTodosLosClientes().size()-1).getId();
                    
                }
                System.out.println("clientes = " + clientes);
                cliente.setId(clientes);
                System.out.println("elclienteid es : = " + cliente.getId());
                falla.setCliente_id(cliente);
                falla.setTecnico_id(tecnicooptimo);
                falla.setFecha(horarioOptimo);
                servicioF.guardarFalla(falla);
                String resultado = tecnicooptimo.getNombre()+","+horarioOptimo+","+cliente.getNombre();
                
                
                return "redirect:/cliente/reportok/"+resultado;
            }
        }else{
            System.out.println("paila, no hay orario");
            String resultado = cliente.getNombre();
            return "redirect:/cliente/reportmal/"+resultado;
        }

        return "redirect:/cliente/reportar";
        
    }
}
