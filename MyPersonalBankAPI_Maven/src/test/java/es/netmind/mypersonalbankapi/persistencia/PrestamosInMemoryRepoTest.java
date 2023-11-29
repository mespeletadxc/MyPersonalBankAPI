package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class PrestamosInMemoryRepoTest {

    @Autowired
    private IPrestamosRepo repo;

    @Test
    void testBeans() {
        assertThat(repo, notNullValue());
    }

    @Test
    void getAll() {
    }

    @Test
    void getLoanById() {
    }

    @Test
    void addLoan() {
    }

    @Test
    void deleteLoan() {
    }

    @Test
    void updateLoan() {
    }

    @Test
    void getLoansByClient() {
    }

    @Test
    void getLoansByClientAndId() {
    }
}