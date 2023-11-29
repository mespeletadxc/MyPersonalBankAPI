package es.netmind.mypersonalbankapi.modelos.clientes;

import es.netmind.mypersonalbankapi.config.SpringConfig;
import es.netmind.mypersonalbankapi.modelos.cuentas.Ahorro;
import es.netmind.mypersonalbankapi.modelos.cuentas.Cuenta;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import es.netmind.mypersonalbankapi.persistencia.ClientesInMemoryRepo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class ClienteTestIntegracion {
    @Autowired
     private static IClientesRepo clientesRepo;
    @Test
    void DadoUnPrestamoCorrectoQueCumpleCondicionesCuandoEvaluarSolicitudPrestamoEntoncesConcedidoTrue() throws Exception{
        Prestamo pr1 = new Prestamo(1, LocalDate.now(), 1000.0, 1000.0, 4, 2, false, false, 2);
        Cliente c1 = new Personal(1, "Juan", "jj@j.com", "Calle JJ 1", LocalDate.now(), true, false, "12345678J");
        Cuenta cu1 = new Ahorro(1, LocalDate.now(), 1000.0, 1.1, 0.2);
        c1.asociarCuenta(cu1);
        c1.asociarPrestamo(pr1);

        //cuando
        boolean valor = c1.evaluarSolicitudPrestamo(pr1);

        //entonces
        assertTrue(valor);
    }


    @Test
    void DadoUnPrestamoCorrectoQueNoCumpleCondicionesCuandoEvaluarSolicitudPrestamoEntoncesConcedidoTrue() throws Exception{
        Prestamo pr1 = new Prestamo(1, LocalDate.now(), 1000.0, 1000.0, 4, 2, false, false, 2);
        Cliente c1 = new Personal(1, "Juan", "jj@j.com", "Calle JJ 1", LocalDate.now(), true, false, "12345678J");
        Cuenta cu1 = new Ahorro(1, LocalDate.now(), 100.0, 1.1, 0.2);
        c1.asociarCuenta(cu1);
        c1.asociarPrestamo(pr1);

        //cuando
        boolean valor = c1.evaluarSolicitudPrestamo(pr1);

        //entonces
        assertFalse(valor);
    }

}