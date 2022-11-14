
package com.app.serviciot.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="tecnicos")
public class Tecnico implements Serializable{
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String nombre;
    private String horario;
    private String horarioOcupado;
    @OneToMany(mappedBy =  "tecnico")
    private List<Falla> fallas;
    @OneToMany(mappedBy =  "tecnico")
    private List<Falla> informes;

    public String getNombre() {
        return nombre;
    }

    public Tecnico(String nombre, String horario, String horarioOcupado, List<Falla> fallas, List<Falla> informes) {
        this.nombre = nombre;
        this.horario = horario;
        this.horarioOcupado = horarioOcupado;
        this.fallas = fallas;
        this.informes = informes;
    }
    
    
    public boolean  validarOcupado(String text){
        String[] horarios= horario.split(",");
        String[] horariosO= horarioOcupado.split(",");
//        if (horarioOcupado==null) {
//            return false;
//        }
        for (String hora : horariosO) {
            if (hora.equals(text)) {
                return true;
            }
        }
        return false;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    public Tecnico(String horario, String horarioOcupado, List<Falla> fallas, List<Falla> informes) {
        this.horario = horario;
        this.horarioOcupado = horarioOcupado;
        this.fallas = fallas;
        this.informes = informes;
    }

    public Tecnico(String horario, List<Falla> fallas, List<Falla> informes) {
        this.horario = horario;
        this.fallas = fallas;
        this.informes = informes;
    }

    public Tecnico() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getHorarioOcupado() {
        return horarioOcupado;
    }

    public void setHorarioOcupado(String horarioOcupado) {
        this.horarioOcupado = horarioOcupado;
    }

    

    
    public List<Falla> getFallas() {
        return fallas;
    }

    public void setFallas(List<Falla> fallas) {
        this.fallas = fallas;
    }

    public List<Falla> getInformes() {
        return informes;
    }

    public void setInformes(List<Falla> informes) {
        this.informes = informes;
    }
    
    
}
