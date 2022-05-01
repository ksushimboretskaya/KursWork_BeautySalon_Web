package com.spring.kurswork_beautysalon_web.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "services_name", nullable = false)
    private String servicesName;

    @Column(name = "services_price", nullable = false)
    private String servicesPrice;

    @Column(name = "duration", nullable = false)
    private String duration;

    public Services() {
    }

    public Services(Long id, String servicesName, String servicesPrice, String duration) {
        this.id = id;
        this.servicesName = servicesName;
        this.servicesPrice = servicesPrice;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public String getServicesPrice() {
        return servicesPrice;
    }

    public void setServicesPrice(String servicesPrice) {
        this.servicesPrice = servicesPrice;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Services{" +
                "id=" + id +
                ", servicesName='" + servicesName + '\'' +
                ", servicesPrice='" + servicesPrice + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}