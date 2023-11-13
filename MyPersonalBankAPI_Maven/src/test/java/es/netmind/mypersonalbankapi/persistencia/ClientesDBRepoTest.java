
package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientesDBRepoTest {
    private IClientesRepo repo;

    @BeforeEach
    void setUp() throws Exception {
//        repo = new UsuarioInMemoryRepository();
        repo = new ClientesDBRepo();
    }

    @Test
    void dadoCliente_cuandoExisteClienteEnDB_entoncesOK() throws Exception {
        Cliente cli = repo.getClientById(1);
        assertTrue(cli.getId() == 1);
    }

    @Test
    void dadoCliente_cuandoExisteClienteNoEnDB_entoncesNOK() throws Exception {
        Cliente cli = repo.getClientById(4);
        assertTrue(cli == null);
    }

    @Test
    void dadoRepo_cuandoHayClientesEnDB_entoncesOK() throws Exception {
        List<Cliente> clientes = repo.getAll();
        assertThat(clientes.size(), greaterThan(0));
       // System.out.println("Lista de clientes: "+clientes.toString());
        System.out.println("Lista de clientes: "+clientes);
    }

}