package ru.ryazhev.task1;


import java.time.LocalDate;
import java.util.UUID;

public class Stuff {
    public Stuff(){};

    public Stuff(UUID id,
                 String surname,
                 String name,
                 String patronymic,
                 boolean sex,
                 LocalDate birthDate,
                 Double salaryMultiplier,
                 Position position) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.sex = sex;
        this.birthDate = birthDate;
        this.salaryMultiplier = salaryMultiplier;
        this.position = position;
    }

    private UUID id;
    private String surname;
    private String name;
    private String patronymic;
    private boolean sex;
    private LocalDate birthDate;
    private Double salaryMultiplier;
    private Position position;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Double getSalaryMultiplier() {
        return salaryMultiplier;
    }

    public void setSalaryMultiplier(Double salaryMultiplier) {
        this.salaryMultiplier = salaryMultiplier;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
