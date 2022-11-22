package com.app.serviciot.controladores;

import com.app.serviciot.entidades.Cliente;
import com.app.serviciot.entidades.Falla;
import com.app.serviciot.entidades.Informe;
import com.app.serviciot.entidades.Tecnico;
import com.app.serviciot.repositorio.InformeRepositorio;
import com.app.serviciot.servicios.IClienteServicio;
import com.app.serviciot.servicios.IFallaServicio;
import com.app.serviciot.servicios.IInformeServicio;
import com.app.serviciot.servicios.ITecnicoServicio;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClienteControlador {

    @Autowired
    private IClienteServicio servicio;
    @Autowired
    private ITecnicoServicio servicioT;

    @Autowired
    private IFallaServicio servicioF;

    @Autowired
    private IInformeServicio servicioInforme;

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
    public String pruebaGuardar(Model model) {
        Cliente cli = servicio.buscarPorIdCliente(1L).get();
        Tecnico tec = servicioT.findById(1L).get();
        Falla falla = new Falla("hola", cli, tec, "10:00:00");
        servicioF.guardarFalla(falla);
        return "index";
    }

    @GetMapping("/cliente/consultarinforme")
    public String consultarInforme(Model model) {
        String correo = "";
        model.addAttribute("correo", correo);
        return "informecliente";
    }

    @RequestMapping("/consultaestado")
    public String consultarestado(@RequestParam String correo, Model modelo) {
        boolean correcto = false; 
        System.out.println("correo = " + correo);
        List<Long> idclientes = new ArrayList<>();
        List<Cliente> listaclientes = servicio.listarTodosLosClientes();
        for (Cliente listacliente : listaclientes) {
            if (listacliente.getCorreo().equals(correo)) {
                idclientes.add(listacliente.getId());
            }
        }
        String res ="";
        String res2 ="";
        List<Long> idFallas = new ArrayList<>();
        List<Falla> misFallas = servicioF.listarTodosLosFallas();
        if (!idclientes.isEmpty()) {
            for (Falla misFalla : misFallas) {
                for (Long cliente : idclientes) {
                    if (misFalla.getCliente_id().getId() == cliente) {
                        idFallas.add(misFalla.getId());
                        System.out.println("el id de la fallas del clientees  = " + misFalla.getId());
                    }
                }
            }
            
            for (Long misFalla : idFallas) {
                Falla falla = servicioF.buscarFallaPorID(misFalla).get();
                System.out.println("informeTxt = " + falla.getInforme_id().getComentarios());
                if (falla.getInforme_id().getComentarios()==null) {
                    System.out.println("entro aca ");
                    res= "La cita aun se encuentra en proceso"; 
                    res2 = "Tu tecnico esta en camino";
                }else{
                    System.out.println("no entro aca ");
                    
                    if (falla.getInforme_id().getSolucionado().equals("True")) {
                        res = "Tu tecnico nos comento que ya realizo la visita, y todo marcho a la perfeccion :)";
                        res2 = "esto fue los que nos comento: " +falla.getInforme_id().getComentarios();
                        correcto = true;
                    }else{
                         res= "Tu tecnico nos comento que ha ocuriido un problema";
                        res2= "esto fue los que nos comento: " +falla.getInforme_id().getComentarios();
                    }
                   
                }
            }
            
            
            
            
        }else{
            res= " el correo no es valido";
            res2= "Intentalo nuevamente";
            
        }
        modelo.addAttribute("correcto", correcto);
        modelo.addAttribute("resultado1", res);
        modelo.addAttribute("resultado2", res2);
        return "respuestainfo";
    }

    @GetMapping("/cliente/reportok/{parametro}")
    public String mostrarOk(@PathVariable("parametro") String parametro, Model model) {
        String[] resultados = parametro.split(",");
        model.addAttribute("tecnico", resultados[0]);
        model.addAttribute("horario", resultados[1]);
        model.addAttribute("nombre", resultados[2]);

        return "reportok";
    }

    @GetMapping("/cliente/reportmal/{parametro}")
    public String mostrarMal(@PathVariable("parametro") String parametro, Model model) {

        model.addAttribute("resultado", parametro);

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
                    clientes = 1L;
                } else {
                    servicio.listarTodosLosClientes().forEach((t) -> {
                        System.out.println("t = " + t.getId());
                    });
                    int posicion = servicio.listarTodosLosClientes().size();
                    System.out.println("tamaño lista" + servicio.listarTodosLosClientes().size());
                    System.out.println("ultimo ud" + servicio.listarTodosLosClientes().get(posicion - 1).getId());
//                    System.out.println("resultadoamostrar = " + servicio.listarTodosLosClientes().get(servicio.listarTodosLosClientes().size()).getId());
                    clientes = servicio.listarTodosLosClientes().get(servicio.listarTodosLosClientes().size() - 1).getId();

                }
                System.out.println("clientes = " + clientes);
                Informe nuevoInforme = new Informe();
                servicioInforme.guardarInforme(nuevoInforme);
                falla.setInforme_id(nuevoInforme);
                cliente.setId(clientes);
                System.out.println("elclienteid es : = " + cliente.getId());
                falla.setCliente_id(cliente);
                falla.setTecnico_id(tecnicooptimo);
                falla.setFecha(horarioOptimo);
                //Informe info = new Informe();
                //falla.setInforme_id(info);
                servicioF.guardarFalla(falla);
                String resultado = tecnicooptimo.getNombre() + "," + horarioOptimo + "," + cliente.getNombre();

                return "redirect:/cliente/reportok/" + resultado;
            }
        } else {
            System.out.println("paila, no hay orario");
            String resultado = cliente.getNombre();
            return "redirect:/cliente/reportmal/" + resultado;
        }

        return "redirect:/cliente/reportar";

    }
}
