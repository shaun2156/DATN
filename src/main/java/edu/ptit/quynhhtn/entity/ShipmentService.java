package edu.ptit.quynhhtn.entity;

import javax.persistence.*;

@Entity
public class ShipmentService extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shmServiceId;

    private String name;

    private String description;

    private String icUrl;

    public Long getShmServiceId() {
        return shmServiceId;
    }

    public void setShmServiceId(Long shmServiceId) {
        this.shmServiceId = shmServiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcUrl() {
        return icUrl;
    }

    public void setIcUrl(String icUrl) {
        this.icUrl = icUrl;
    }
}
