package com.example.ActivemqRest.model;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "MANAGER")
public class Manager implements Comparable<Manager> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "SERVICE_NAME", length = 256)
    private String serviceName;

    @Column(name = "MANAGER_LOGIN", length = 256)
    private String managerLogin;

    @Column(name = "SERVICE_TIME_START")
    private Date serviceTimeStart;

    @Column(name = "SERVICE_TIME_END")
    private Date serviceTimeEnd;

    public Manager(String serviceName, String managerLogin, Date serviceTimeStart, Date serviceTimeEnd) {
        this.serviceName = serviceName;
        this.managerLogin = managerLogin;
        this.serviceTimeStart = serviceTimeStart;
        this.serviceTimeEnd = serviceTimeEnd;
    }

    public Manager() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getManagerLogin() {
        return managerLogin;
    }

    public void setManagerLogin(String managerLogin) {
        this.managerLogin = managerLogin;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return Objects.equals(id, manager.id) &&
                Objects.equals(serviceName, manager.serviceName) &&
                Objects.equals(managerLogin, manager.managerLogin) &&
                Objects.equals(serviceTimeStart, manager.serviceTimeStart) &&
                Objects.equals(serviceTimeEnd, manager.serviceTimeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceName, managerLogin, serviceTimeStart, serviceTimeEnd);
    }

    @Override
    public int compareTo(Manager o) {
        if (getServiceTimeStart() == null || o.getServiceTimeStart() == null) {
            return 0;
        }
        return getServiceTimeStart().compareTo(o.serviceTimeStart);
    }
}
