package com.example.ActivemqRest.repository;

import com.example.ActivemqRest.model.Manager;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class ManagerRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ManagerRepository managerRepository;

    @Test
    public void whenFindByManagerLogin_thenReturnManager() {
        Manager manager = new Manager("Оформление ипотеки",
                "Ivan", new Date(), new Date());
        entityManager.persistAndFlush(manager);
        Manager foundManager = managerRepository.findAll().get(0);
        assertThat(foundManager.getManagerLogin()).isEqualTo(manager.getManagerLogin());
    }
}
