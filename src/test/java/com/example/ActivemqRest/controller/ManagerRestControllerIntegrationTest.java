package com.example.ActivemqRest.controller;

import com.example.ActivemqRest.model.ManagerDTO;
import com.example.ActivemqRest.service.ManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ManagerController.class)
public class ManagerRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ManagerService managerService;

    @Test
    public void getManagers()
            throws Exception {
        ManagerDTO managerDTO = new ManagerDTO("Оформление ипотеки", new Date(), new Date());
        List<ManagerDTO> managers = Collections.singletonList(managerDTO);
        Map<String, List<ManagerDTO>> map = new HashMap<>();
        map.put("Ivan", managers);
        given(managerService.getManagers()).willReturn(map);
        mvc.perform(get("/get")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.get(Ivan).size()", is(1)));
    }
}
