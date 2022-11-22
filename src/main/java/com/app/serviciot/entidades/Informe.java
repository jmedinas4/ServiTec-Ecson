package com.app.serviciot.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "informes")
public class Informe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    private Long id;
    
    private String solucionado;
    private String comentarios;

    public Informe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSolucionado() {
        return solucionado;
    }

    public String isSolucionado() {
        return solucionado;
    }

    public void setSolucionado(String solucionado) {
        this.solucionado = solucionado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

}
