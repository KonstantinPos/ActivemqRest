package com.example.ActivemqRest.service;

import com.example.ActivemqRest.model.Manager;
import com.example.ActivemqRest.model.ManagerDTO;
import com.example.ActivemqRest.model.ManagerResponse;

import java.util.List;
import java.util.Map;

public interface ManagerService {

    Map<String, List<ManagerDTO>> getManagers();

    ManagerResponse saveManager(Manager manager);
}
