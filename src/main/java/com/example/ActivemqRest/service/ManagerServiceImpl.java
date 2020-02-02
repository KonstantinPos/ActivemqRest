package com.example.ActivemqRest.service;

import com.example.ActivemqRest.model.Manager;
import com.example.ActivemqRest.model.ManagerDTO;
import com.example.ActivemqRest.model.ManagerResponse;
import com.example.ActivemqRest.model.Success;
import com.example.ActivemqRest.repository.ManagerRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Queue;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;

    @Override
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

    @Override
    public ManagerResponse saveManager(Manager manager) {
        try {
            Gson gson = new Gson();
            String jsonManager = gson.toJson(manager);
            jmsTemplate.convertAndSend(queue, jsonManager);
            return new ManagerResponse(Success.OK, "Manager sent to queue");
        } catch (Exception e) {
            return new ManagerResponse(Success.ERROR,
                    "Error sending manager to queue: " + e.getMessage());
        }
    }
}
