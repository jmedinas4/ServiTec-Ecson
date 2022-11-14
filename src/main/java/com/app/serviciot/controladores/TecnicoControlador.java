package com.app.serviciot.controladores;

import com.app.serviciot.entidades.Tecnico;
import com.app.serviciot.servicios.ITecnicoServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TecnicoControlador {

    @Autowired
    private ITecnicoServicio servicio;

    @GetMapping("/tecnico/{id}")
    public String vistaTecnico(@PathVariable Integer id, Model modelo) {

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

        } else {
            modelo.addAttribute("tecnico", null);
        }
        
//   
        return "tecnico";
    }
}
