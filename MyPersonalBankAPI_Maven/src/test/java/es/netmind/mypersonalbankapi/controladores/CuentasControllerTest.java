package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class CuentasControllerTest {

    @Autowired
    private CuentasController service;

    @Test
    void testBeans() {
        assertNotNull(service);
    }

    @Test
    void mostrarLista() {
    }

    @Test
    void mostrarDetalle() {
    }

    @Test
    void eliminar() {
    }

    @Test
    void add() {
    }

    @Test
    void actualizar() {
    }

    @Test
    void setCuentasRepo() {
    }

    @Test
    void setClientesRepo() {
    }
}