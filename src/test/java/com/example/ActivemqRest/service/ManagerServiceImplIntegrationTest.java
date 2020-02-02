package com.example.ActivemqRest.service;

import com.example.ActivemqRest.model.Manager;
import com.example.ActivemqRest.model.ManagerDTO;
import com.example.ActivemqRest.repository.ManagerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;


import javax.jms.Queue;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ManagerServiceImplIntegrationTest {

    @TestConfiguration
    static class ManagerServiceImplTestContextConfiguration {

        @Bean
        public ManagerService managerService() {
            return new ManagerServiceImpl();
        }
    }

    @Autowired
    private ManagerService managerService;

    @MockBean
    private ManagerRepository managerRepository;

    @MockBean
    JmsTemplate jmsTemplate;

    @MockBean
    Queue queue;

    @Before
    public void setUp() {
        Manager manager = new Manager("Оформление ипотеки",
                "Ivan", new Date(), new Date());
        List<Manager> managers = Collections.singletonList(manager);
        Mockito.when(managerRepository.findAll()).thenReturn(managers);
    }

    @Test
    public void whenValidManager_thenUserShouldBeFound() {
        Map<String, List<ManagerDTO>> map = managerService.getManagers();
        assertThat(map.get("Ivan").size())
                .isEqualTo(1);
    }
}
