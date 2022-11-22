
package com.app.serviciot.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="fallas")
public class Falla implements Serializable{
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente_id;
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico_id;
    private String fecha;
    @ManyToOne
    @JoinColumn(name = "informe_id",  nullable = true)
    private Informe informe_id;

    public Falla() {
    }

    public Informe getInforme_id() {
        return informe_id;
    }

    public void setInforme_id(Informe informe_id) {
        this.informe_id = informe_id;
    }

    public Falla(String descripcion, Cliente cliente_id, Tecnico tecnico_id, String fecha) {
        this.descripcion = descripcion;
        this.cliente_id = cliente_id;
        this.tecnico_id = tecnico_id;
        this.fecha = fecha;
    }

   

    public Cliente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Tecnico getTecnico_id() {
        return tecnico_id;
    }

    public void setTecnico_id(Tecnico tecnico_id) {
        this.tecnico_id = tecnico_id;
    }

   
   

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Cliente getCliente() {
//        return cliente_id;
//    }
//
//    public void setCliente(Cliente cliente) {
//        this.cliente_id = cliente;
//    }

   
 
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
