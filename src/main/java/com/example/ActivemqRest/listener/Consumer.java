package com.example.ActivemqRest.listener;

import com.example.ActivemqRest.model.Manager;
import com.example.ActivemqRest.repository.ManagerRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private ManagerRepository managerRepository;

    @JmsListener(destination = "manager.queue")
    public void consume(String managerJson) {
        Gson gson = new Gson();
        Manager manager = gson.fromJson(managerJson, Manager.class);
        managerRepository.save(manager);
    }
}
