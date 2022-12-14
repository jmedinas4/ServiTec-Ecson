
package com.app.serviciot.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = " clientes")
public class Cliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
     private String direccion; 
    private String nombre;
    private  String correo;
//    @OneToMany(targetEntity=Falla.class )
//    private List<Falla> fallas;
//    @OneToMany(mappedBy =  "cliente_id",targetEntity=Informe.class )
//    private List<Informe> informes;

    public Cliente() {
        
    }

    public Cliente(String direccion, String nombre, String correo) {
        this.direccion = direccion;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

//    public List<Falla> getFallas() {
//        return fallas;
//    }
//
//    public void setFallas(List<Falla> fallas) {
//        this.fallas = fallas;
//    }
//
//    public List<Informe> getInformes() {
//        return informes;
//    }
//
//    public void setInformes(List<Informe> informes) {
//        this.informes = informes;
//    }
    
 
}
