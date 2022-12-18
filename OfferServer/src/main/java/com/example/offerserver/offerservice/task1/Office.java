package com.example.offerserver.offerservice.task1;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="office_table")

public class Office {
    public Office(){};

    public Office(UUID id, String address, String lawAddress, Integer cabinetsCount) {
        this.id = id;
        this.address = address;
        this.lawAddress = lawAddress;
        this.cabinetsCount = cabinetsCount;
    }
    @Id
    private UUID id;
    @Column(name = "address")
    private String address;
    @Column(name = "law_address")
    private String lawAddress;
    @Column(name = "cabinets_count")
    private Integer cabinetsCount;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLawAddress() {
        return lawAddress;
    }

    public void setLawAddress(String lawAddress) {
        this.lawAddress = lawAddress;
    }

    public Integer getCabinetsCount() {
        return cabinetsCount;
    }

    public void setCabinetsCount(Integer cabinetsCount) {
        this.cabinetsCount = cabinetsCount;
    }
}
