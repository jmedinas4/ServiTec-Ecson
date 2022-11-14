package com.app.serviciot.servicios;

import com.app.serviciot.entidades.Tecnico;
import com.app.serviciot.repositorio.TecnicoRepositorio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class TecnicoServicioImpl implements ITecnicoServicio {

    @Autowired
    private TecnicoRepositorio repositorio;

    @Override
    public List<Tecnico> listarTodosLosTecnicos() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Tecnico> findById(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public HashMap<Optional<Tecnico>, String> buscarOptimo() {
        List<Tecnico> tecnicos = repositorio.findAll();
        List<Long> posiciones = new ArrayList<>();
        List<String> valores = new ArrayList<>();
   
         HashMap<Optional<Tecnico>, String> horariosDisponibles = new  HashMap<Optional<Tecnico>, String>();
    
        List<String> horarios;
        List<String> horariosO = null;
        for (Tecnico tecnico : tecnicos) {
            horarios = Arrays.asList(tecnico.getHorario().split(","));
            if (tecnico.getHorarioOcupado() == null) {
                for (String horario : horarios) {
                    posiciones.add(tecnico.getId());
                    valores.add(horario);

                }
                
            } else {
                horariosO = Arrays.asList(tecnico.getHorarioOcupado().split(","));
                for (String horario : horarios) {
                    if (!horariosO.contains(horario)) {
                        posiciones.add(tecnico.getId());
                        valores.add(horario);

                    }
                }
            }

        }
//        int contador2 = 0;
//        for (Long horariosDisponible : posiciones) {
//            System.out.println(horariosDisponible + "--" + valores.get(contador2));
//            contador2++;
//        }
        //busco el minimo horario
        
        if (!valores.isEmpty()) {
            Long contador = 0L;
            float valorEntero = 0;
            float valorOptimo = Float.parseFloat(valores.get(0).replace(":", ""));
            Long posicionOptimo=0L;
            for (String valor : valores) {
                valorEntero = Float.parseFloat(valor.replace(":", ""));
                if (valorEntero < valorOptimo) {
                    valorOptimo = valorEntero;
                    
                    posicionOptimo =contador;
                }
                contador++;
                

            }
//            System.out.println("contador:"+contador);
//            System.out.println("valorOptimo = " + (int) valorOptimo);
            String horarioNuevo = convertirStringHora(String.valueOf( (int)valorOptimo));
//            System.out.println("horarioNuevo = " + horarioNuevo);
        //aca retorno el tecnico encontrado y su horario
            horariosDisponibles.put(repositorio.findById(posiciones.get(posicionOptimo.intValue())),horarioNuevo);
            
            //agregar el horario ocupado del tecnico seleccionado
            Tecnico actualizado = repositorio.getOne(posiciones.get(posicionOptimo.intValue()));
            actualizado.setHorarioOcupado(actualizado.getHorarioOcupado()+","+horarioNuevo);
            repositorio.save(actualizado);
            return horariosDisponibles;
        }
        

        return null;
    }
 
    public String convertirStringHora(String txt){
        String cadenaNueva="";
        int contador=0;
        for (int i = 0; i < txt.length(); i++) {
            if (contador==2) {
                cadenaNueva+=":";
                contador=0;
            }
            cadenaNueva+=txt.charAt(i);
            System.out.print(txt.charAt(i));
            contador++;
        }
        
        return cadenaNueva;
    }
}
