
package com.app.serviciot;

import com.app.serviciot.entidades.Tecnico;
import com.app.serviciot.repositorio.TecnicoRepositorio;
import com.app.serviciot.servicios.ITecnicoServicio;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest()
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Sql({ "/schema.sql", "/data.sql" })
public class TecnicoTest {
    
    @Autowired()
    private TecnicoRepositorio servicio;
//     @Test
//    public void NofindById(Long id){
//        Tecnico tecnico = servicio.findById(id).get();
//        assertNull(tecnico);
//    }
    
//     @Test
//    public void findById(Long id){
//        id= 1l;
//        Tecnico tecnico = servicio.findById(id).get();
//        assertNotNull(tecnico);
//    }
//    
//     @Test
//     public void testearServicioListartecnicosVacio() {
//     List<Tecnico> Tecnicos = servicio.findAll();
//     assertNotNull(Tecnicos);
//     assertTrue(Tecnicos.isEmpty());
//     }
    

//    @Test
//    public void probarConsultaId(Long id ){
//        
//        List<Tecnico> rta = servicio.findAll();
//
//        assertTrue(!rta.isEmpty());
//    }
    
//    @Test
//    public void probarConsultaAllTecnico(){
//  
//        List<Tecnico> rta = servicio.findAll();
//
//        assertTrue(!rta.isEmpty());
//    }

//    @Test
//    void elRepositorioExiste() {
//       assertNotNull(servicio);
//    }
    

}
