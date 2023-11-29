package es.netmind.mypersonalbankapi.config;


import es.netmind.mypersonalbankapi.controladores.ClientesController;
import es.netmind.mypersonalbankapi.controladores.IClientesController;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {
    /*@Autowired
    ICompraRepository compraRepo;*/

    @Autowired
    private IClientesRepo repoCli;

    @Bean
    public IClientesController getClienterController() {
        System.out.println("usando IClientesController...");
        ClientesController cli = new ClientesController();
        cli.setClientesRepo(repoCli);
        return cli;
    }



}
