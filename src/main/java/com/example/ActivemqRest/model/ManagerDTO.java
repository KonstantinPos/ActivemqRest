package com.example.ActivemqRest.model;

import java.util.Date;

public class ManagerDTO {

    String serviceName;
    private Date serviceTimeStart;
    private Date serviceTimeEnd;

    public ManagerDTO(String serviceName, Date serviceTimeStart, Date serviceTimeEnd) {
        this.serviceName = serviceName;
        this.serviceTimeStart = serviceTimeStart;
        this.serviceTimeEnd = serviceTimeEnd;
    }

    public ManagerDTO() {
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getServiceTimeStart() {
        return serviceTimeStart;
    }

    public void setServiceTimeStart(Date serviceTimeStart) {
        this.serviceTimeStart = serviceTimeStart;
    }

    public Date getServiceTimeEnd() {
        return serviceTimeEnd;
    }

    public void setServiceTimeEnd(Date serviceTimeEnd) {
        this.serviceTimeEnd = serviceTimeEnd;
    }
}
