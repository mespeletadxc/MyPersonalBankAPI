package es.netmind.mypersonalbankapi.config;


import es.netmind.mypersonalbankapi.persistencia.ClientesDBRepo;
import es.netmind.mypersonalbankapi.persistencia.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ReposConfig {

    /*@Bean
    public String getUrlConn() throws IOException {
        PropertyValues props = new PropertyValues();
        String connUrl = props.getPropValues().getProperty("db_url");
        return connUrl;
    }*/

    //    @Autowired
    @Value("${db_url}")
    String connUrl;

    @Bean
    public DBConnector createDBConnector() {
        return new DBConnector();
    }


    @Bean
    @Profile("default")
    public IClientesRepo createIClientesRepo() throws Exception {
        System.out.println("usando clientesDBRepo...");
        ClientesDBRepo repo = new ClientesDBRepo();

        repo.setDb_url(connUrl);
        return repo;
    }

    @Bean
    @Profile("inMemory")
    public ClientesInMemoryRepo createClientesinMemoryRepo() throws Exception {
        System.out.println("usando clientesinMemoryRepo...");
        ClientesInMemoryRepo repo = new ClientesInMemoryRepo();

        return repo;
    }


}
