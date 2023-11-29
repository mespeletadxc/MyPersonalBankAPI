package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.config.SpringConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class ClientesControllerTest {

    @Autowired
    private ClientesController service;

    @Test
    void testBeans() {
        assertNotNull(service);
    }

    @Test
    void mostrarLista() throws Exception {
        service.mostrarLista();
    }

    @Test
    void mostrarDetalle(){
        service.mostrarDetalle(1);
    }

}