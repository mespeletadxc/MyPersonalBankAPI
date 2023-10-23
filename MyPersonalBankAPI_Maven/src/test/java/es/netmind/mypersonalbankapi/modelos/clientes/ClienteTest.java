package es.netmind.mypersonalbankapi.modelos.clientes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @ParameterizedTest
    @ValueSource(strings = {"Antonio", "María"})
    void DadoUnNombreCorrectoEmailCorrectoDireccionCorrectaAltaCorrecta_CuandoValidarComunEntoncesTrue(String nombre) throws Exception {
        //Dado
        Cliente c1 = new Personal(1, nombre, "jj@j.com", "Calle JJ 1", LocalDate.now(), true, false, "12345678J");
        //cuando
        boolean valor = c1.validarComun();
        //entonces
        assertTrue(valor);

    }

    @ParameterizedTest
    @ValueSource(strings = {"HI", "1"})
    void DadoUnNombreINCorrecto_CuandoValidarComunEntoncesFalse(String nombre) throws Exception{
        //Dado
        Cliente c1 = new Personal(1, nombre, "jj@j.com", "Calle JJ 1", LocalDate.now(), true, false, "12345678J");
        //cuando
        boolean valor = c1.validarComun();
        //entonces
        assertFalse(valor);

    }


    @Test
    void DadoUnNombreNUlo_CuandoValidarComunEntoncesFalse() throws Exception {
        //Dado
        Cliente c1 = new Personal(1, null, "jj@j.com", "Calle JJ 1", LocalDate.now(), true, false, "12345678J");
        //cuando
        boolean valor = c1.validarComun();
        //entonces
        assertFalse(valor);
    }


}