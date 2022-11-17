package com.app.serviciot.controladores;

import com.app.serviciot.entidades.Cliente;
import com.app.serviciot.entidades.Falla;
import com.app.serviciot.entidades.Informe;
import com.app.serviciot.entidades.Tecnico;
import com.app.serviciot.servicios.IFallaServicio;
import com.app.serviciot.servicios.IInformeServicio;
import com.app.serviciot.servicios.ITecnicoServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TecnicoControlador {

    @Autowired
    private ITecnicoServicio servicio;
    
     @Autowired
    private IInformeServicio servicioInforme;
    
    class Retorno{
        String id;
        String hora;
    }
    
    @GetMapping("/tecnico/formulario/{id}/{hora}")
    public String pruebaSelect(@PathVariable("id") String id,@PathVariable("hora") String hora, Model modelo){
        Informe informe = new Informe();
        System.out.println("informeContenido = " + informe.getComentarios());
        System.out.println("informeAceptado = " + informe.getSolucionado());
        Long resultado = servicio.buscarIdFallaPorSeleccion(Integer.parseInt(id), hora);
        Falla falla= servicio.buscarFallaPorId(resultado).get();
//        System.out.println("resultado = " + resultado);
        Optional<Tecnico> tec = servicio.findById(Long.parseLong(id));
        Tecnico tecnico= tec.get();
        modelo.addAttribute("id",id);
        modelo.addAttribute("nombret",tecnico.getNombre());
        modelo.addAttribute("nombrecliente",falla.getCliente_id().getNombre());
        modelo.addAttribute("hora",hora);
        modelo.addAttribute("idfalla",resultado);
        modelo.addAttribute("informe",informe);
        return "aceptarvisita";
    }
    @PostMapping("/tecnico/reporto/{id}/{hora}")
    public String acepatOk(@ModelAttribute("informe") Informe informe, @PathVariable String id,@PathVariable String hora, Model modelo) {
        System.out.println("informeCOment = " + informe.getComentarios());
        System.out.println("informeSolu = " + informe.getSolucionado());
        servicioInforme.guardarInforme(informe);
        servicio.ajustarHorario(Integer.parseInt(id), hora, informe);
        Long resultado = servicio.buscarIdFallaPorSeleccion(Integer.parseInt(id), hora);
        modelo.addAttribute("id", id);
        Optional<Tecnico> optional;
        optional = servicio.findById(Long.valueOf(id));

        if (optional.isPresent()) {
            String horarios = optional.get().getHorario();
            String[] listaHorarios = horarios.split(",");
            String[] listaHorariosOcupados=null;
            
            if (optional.get().getHorarioOcupado()!=null) {
                String horariosocupado = optional.get().getHorarioOcupado();
                listaHorariosOcupados = horariosocupado.split(",");
                modelo.addAttribute("listaHorariosOcupados", listaHorariosOcupados);
                Tecnico tecnicoNuevo = optional.get();
//                System.out.println(tecnicoNuevo.validarOcupado("10:00:00"));
            }
            
            for (String valor : listaHorariosOcupados) {
                System.out.println("listaHorariosOcupado = " + valor);
            }
            
            modelo.addAttribute("listaHorarios", listaHorarios);
            modelo.addAttribute("tecnico", optional.get());
            modelo.addAttribute("id", optional.get().getId());
//            modelo.addAttribute("retorno", new Retorno());

        } else {
            modelo.addAttribute("tecnico", null);
        }
        
//   
        return "tecnico";
    }
    
    
    @GetMapping(value = {"/tecnico/{id}", "/tecnico"})
    public String vistaTecnico(@PathVariable Integer id, Model modelo) {
        System.out.println("entro a get");
        modelo.addAttribute("id", id);
        Optional<Tecnico> optional;
        optional = servicio.findById(Long.valueOf(id));

        if (optional.isPresent()) {
            String horarios = optional.get().getHorario();
            String[] listaHorarios = horarios.split(",");
            String[] listaHorariosOcupados=null;
            
            if (optional.get().getHorarioOcupado()!=null) {
                String horariosocupado = optional.get().getHorarioOcupado();
                listaHorariosOcupados = horariosocupado.split(",");
                modelo.addAttribute("listaHorariosOcupados", listaHorariosOcupados);
                Tecnico tecnicoNuevo = optional.get();
//                System.out.println(tecnicoNuevo.validarOcupado("10:00:00"));
            }
            
            for (String valor : listaHorariosOcupados) {
                System.out.println("listaHorariosOcupado = " + valor);
            }
            
            modelo.addAttribute("listaHorarios", listaHorarios);
            modelo.addAttribute("tecnico", optional.get());
            modelo.addAttribute("id", optional.get().getId());
//            modelo.addAttribute("retorno", new Retorno());

        } else {
            modelo.addAttribute("tecnico", null);
        }
        
//   
        return "tecnico";
    }
}
