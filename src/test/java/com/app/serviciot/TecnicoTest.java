
package com.app.serviciot;

import com.app.serviciot.entidades.Tecnico;
import com.app.serviciot.servicios.ITecnicoServicio;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest()
public class TecnicoTest {
    
    @Autowired()
    private ITecnicoServicio servicio;
    
    @Test
    public void probarConsultaAllTecnico(){
  
        List<Tecnico> rta = servicio.listarTodosLosTecnicos();

        assertTrue(!rta.isEmpty());
    }

    
}
