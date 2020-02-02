package com.example.ActivemqRest.controller;

import com.example.ActivemqRest.model.Manager;
import com.example.ActivemqRest.model.ManagerDTO;
import com.example.ActivemqRest.model.ManagerResponse;
import com.example.ActivemqRest.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/get")
    public Map<String, List<ManagerDTO>> getManagers() {
        return managerService.getManagers();
    }

    @PostMapping("/add")
    public ManagerResponse saveManager(@RequestBody Manager manager) {
        return managerService.saveManager(manager);
    }
}
