package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
import es.netmind.mypersonalbankapi.persistencia.*;
import static es.netmind.mypersonalbankapi.persistencia.ClientesInMemoryRepo.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ClientesInMemoryRepoTest {
    private static IClientesRepo clientesRepo = ClientesInMemoryRepo.getInstance();
    @Test
    void DadoTodosPArametrosCorrectosCuandoAddClientEntoncesTrue() throws Exception {

        Cliente c1 = new Personal(1, "Juan", "jj@j.com", "Calle JJ 1", LocalDate.now(), true, false, "12345678J");
        Cliente clienteGuardado = clientesRepo.addClient(c1);
        assertEquals(clienteGuardado.isMoroso(),false);

    }

    @Test
    void DadoTodosPArametrosCorrectosMenosNombreCuandoAddClientEntoncesTrue() throws Exception {

        Cliente c1 = new Personal(1, "Ju", "jj@j.com", "Calle JJ 1", LocalDate.now(), true, false, "12345678J");

        // entonces
        assertThrows(Exception.class, () -> {
            //cuando
            Cliente clienteGuardado = clientesRepo.addClient(c1);
        });

    }

    @Test
    void DadaUnaListadeCLientesCorrectosCuandoGetAllEntoncesTrue() throws Exception {

        Cliente c1 = new Personal(1, "Juan", "jj@j.com", "Calle JJ 1", LocalDate.now(), true, false, "12345678J");
        int numClientesAnteriores = clientesRepo.getAll().size();
        //cuando
        Cliente clienteGuardado = clientesRepo.addClient(c1);
        int numClientesPosterior = clientesRepo.getAll().size();
        //entonces
        assertTrue(numClientesAnteriores<numClientesPosterior);


    }
}