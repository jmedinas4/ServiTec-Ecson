
package com.app.serviciot.operaciones;

import java.util.List;

public interface IOperaciones<T> {
    public boolean crear(T dto );
    public boolean actualizar(T dto );
    public boolean borrar(Long pk );
    public T consultaPK(Long pk );
    public List<T> ConsultaTodos( );
    
    
}
