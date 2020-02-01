package com.example.ActivemqRest.controllers;

import com.example.ActivemqRest.model.Manager;
import com.example.ActivemqRest.model.ManagerResponse;
import com.example.ActivemqRest.model.ManagerDTO;
import com.example.ActivemqRest.model.Success;
import com.example.ActivemqRest.repository.ManagerRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


@RestController
public class ManagerController {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;

    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping("/get")
    public Map<String, List<ManagerDTO>> getManagers() {
        List<Manager> managers = managerRepository.findAll();
        Map<String, List<Manager>> mapManagers = managers.stream().collect(groupingBy(Manager::getManagerLogin));
        Map<String, List<ManagerDTO>> managerService = new HashMap<>();
        mapManagers.forEach((k, v) -> managerService.put(k, convert(v)));
        return managerService;
    }

    private List<ManagerDTO> convert(List<Manager> managers) {
        managers.sort(Comparator.comparing(Manager::getServiceTimeStart));
        return managers.stream().map(m -> new ManagerDTO(m.getServiceName(),
                m.getServiceTimeStart(), m.getServiceTimeEnd())).collect(Collectors.toList());

    }

    @PostMapping("/add")
    public ManagerResponse saveManager(@RequestBody Manager manager) {
        try {
            Gson gson = new Gson();
            String jsonManager = gson.toJson(manager);
            jmsTemplate.convertAndSend(queue, jsonManager);
            return new ManagerResponse(Success.OK, "Manager sent to queue");
        } catch (Exception e) {
            return new ManagerResponse(Success.ERROR, "Error sending manager to queue: " + e.getMessage());
        }
    }
}
