package com.app.serviciot.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "informes")
public class Informe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Tecnico tecnico;
    private boolean soluciionado;
    private String comentarios;

    public Informe(Cliente cliente, Tecnico tecnico, boolean soluciionado, String comentarios) {
        this.cliente = cliente;
        this.tecnico = tecnico;
        this.soluciionado = soluciionado;
        this.comentarios = comentarios;
    }

    public Informe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public boolean isSoluciionado() {
        return soluciionado;
    }

    public void setSoluciionado(boolean soluciionado) {
        this.soluciionado = soluciionado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

}
