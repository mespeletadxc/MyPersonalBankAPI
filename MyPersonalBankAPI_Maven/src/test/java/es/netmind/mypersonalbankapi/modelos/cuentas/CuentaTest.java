package es.netmind.mypersonalbankapi.modelos.cuentas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientesControllerTest {

    @Test
    void DadAFechaAltaCorrectaINteresPositivoComisionPositivaCuandoValidarComunEntoncesTrue()  {
        //Dado
        Cuenta cu1 = new Ahorro(1, LocalDate.now(), 100.0, 1.1, 0.2);
        //cuando
        boolean valor = cu1.validarComun();
        //entonces
        assertTrue(valor);

    }

    @Test
    void DadAFechaAltaINCorrectaINteresPositivoComisionPositivaCuandoValidarComunEntoncesTrue()  {
        //Dado
        Cuenta cu1 = new Ahorro(1, LocalDate.of(2023,10,24), 100.0, 1.1, 0.2);
        //cuando
        boolean valor = cu1.validarComun();
        //entonces
        assertFalse(valor);

    }


}