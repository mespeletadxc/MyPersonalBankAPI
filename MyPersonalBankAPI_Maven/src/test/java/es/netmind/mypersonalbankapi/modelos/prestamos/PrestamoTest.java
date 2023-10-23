package es.netmind.mypersonalbankapi.modelos.prestamos;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PrestamoTest {

    @Test
    void DadAFechaConcesionCorrectaMOntoCorrectoSaldoCorrectoInteresMOraCorrectoAniosCorrectoCuandoValidarEntoncesTrue()  {
        //Dado
        Prestamo pr1 = new Prestamo(1, LocalDate.now(), 1000.0, 1000.0, 4, 2, false, false, 2);
        //cuando
        boolean valor = pr1.validar();
        //entonces
        assertTrue(valor);

    }

    @Test
    void DadAFechaConcesionCorrectaMOntoCorrectoSaldoCorrectoInteresMOraINCorrectoAniosCorrectoCuandoValidarEntoncesTrue()  {
        //Dado
        Prestamo pr1 = new Prestamo(1, LocalDate.now(), 1000.0, 1000.0, 4, -2, false, false, 2);
        //cuando
        boolean valor = pr1.validar();
        //entonces
        assertFalse(valor);

    }
}