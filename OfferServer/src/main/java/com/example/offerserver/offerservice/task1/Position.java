package com.example.offerserver.offerservice.task1;


import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "position_table")

public class Position {
    public Position() {
    }

    public Position(UUID id, String name, Integer salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    @Id
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "salary")
    private Integer salary;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
