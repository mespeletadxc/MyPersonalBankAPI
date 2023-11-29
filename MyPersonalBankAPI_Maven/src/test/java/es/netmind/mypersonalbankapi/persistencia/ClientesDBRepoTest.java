
package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.config.SpringConfig;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@ActiveProfiles("default")
class ClientesDBRepoTest {
    @Autowired
    private IClientesRepo repo;

    /*@BeforeEach
    void setUp() throws Exception {
//        repo = new UsuarioInMemoryRepository();
        repo = new ClientesDBRepo();
    }*/

    @Test
    void testBeans() {
        assertThat(repo, notNullValue());
    }
    @Test
    void dadoCliente_cuandoExisteClienteEnDB_entoncesOK() throws Exception {
        Cliente cli = repo.getClientById(1);
        assertTrue(cli.getId() == 1);
    }

    @Test
    void dadoCliente_cuandoExisteClienteNoEnDB_entoncesNOK() throws Exception {
        Cliente cli = repo.getClientById(20);
        assertTrue(cli == null);
    }

    @Test
    void dadoRepo_cuandoHayClientesEnDB_entoncesOK() throws Exception {
        List<Cliente> clientes = repo.getAll();
        assertThat(clientes.size(), greaterThan(0));
        // System.out.println("Lista de clientes: "+clientes.toString());
        System.out.println("Lista de clientes: " + clientes);
    }

    @Test
    void dadosClientEmpresa_cuandoinsertarEnDB_entoncesIdValido() throws Exception {
        Empresa cliemp = new Empresa(null,"Servicios Informatico SL", "si@s.com", "Calle SI 3", LocalDate.now(), true, false, "J12345678", new String[]{"Dev", "Marketing"});

        repo.addClientEmpresa(cliemp);

        System.out.println(cliemp);

        assertThat(cliemp.getId(), greaterThan(0));
        System.out.println("Id insertado: "+cliemp.getId());
    }

    @Test
    void dadosClientPersonal_cuandoinsertarEnDB_entoncesIdValido() throws Exception {
        Personal cliper = new Personal(null, "Juan Juanez", "jj@j.com", "Calle JJ 1", LocalDate.now(), true, false, "12345678J");

        repo.addClientPersonal(cliper);

        System.out.println(cliper);

        assertThat(cliper.getId(), greaterThan(0));
        System.out.println("Id insertado: "+cliper.getId());
    }

}