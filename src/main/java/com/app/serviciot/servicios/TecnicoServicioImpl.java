package com.app.serviciot.servicios;

import com.app.serviciot.entidades.Falla;
import com.app.serviciot.entidades.Informe;
import com.app.serviciot.entidades.Tecnico;
import com.app.serviciot.repositorio.FallaRepositorio;
import com.app.serviciot.repositorio.InformeRepositorio;
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
    
    @Autowired
    private FallaRepositorio repositorioF;
    
    @Autowired
    private InformeRepositorio repositorioInforme;

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

    @Override
    public Long buscarIdFallaPorSeleccion(int tecnicoId, String horarioSeleccionado) {
       
        Long result =0L;
        
        List<Falla> fallas = repositorioF.findAll();
        
        for (Falla falla : fallas) {
            if (falla.getTecnico_id().getId()== tecnicoId && falla.getFecha().equals(horarioSeleccionado) ) {
                result=falla.getId();
            }
            
        }
        System.out.println("el id de la falla es = " + result);
        
        return result;
    }
    
    @Override
    public void ajustarHorario(int tecnicoId, String horarioSeleccionado , Informe informe) {
       Long result =0L;
        
        List<Falla> fallas = repositorioF.findAll();
        
        for (Falla falla : fallas) {
            if (falla.getTecnico_id().getId()== tecnicoId && falla.getFecha().equals(horarioSeleccionado) ) {
                result=falla.getId();
            }
            
        }
        Falla fallaActual = repositorioF.findById(result).get();
        Informe informeIdFalla = fallaActual.getInforme_id();
        informeIdFalla.setComentarios(informe.getComentarios());
        informeIdFalla.setSolucionado(informe.getSolucionado());
        repositorioInforme.save(informeIdFalla);
        repositorioF.save(fallaActual);
        
        Tecnico tecnico = repositorio.findById(Long.parseLong(String.valueOf(tecnicoId))).get();
        System.out.println("el tecnico es  = " + tecnico.getNombre() );
        System.out.println("hiorarioOcupado:"+ tecnico.getHorarioOcupado());
        System.out.println("horario por parametro"+horarioSeleccionado);
        List<String> horarioO = Arrays.asList(tecnico.getHorarioOcupado().split(",")) ;
        String actual="";
        if (!horarioO.isEmpty()) {
            for (String horarioOcupadoIndividual : horarioO) {
                if (horarioSeleccionado.equals(horarioOcupadoIndividual)) {
                    
                }else{
                    if (horarioOcupadoIndividual.equals("null")) {
                        
                    }else{
                        actual+=horarioOcupadoIndividual+",";
                    }
                    
                }
            }
        }
        tecnico.setHorarioOcupado(actual);
        repositorio.save(tecnico);

    }

    @Override
    public Optional<Falla> buscarFallaPorId(Long id) {
        return repositorioF.findById(id);
    }

    
    
}
