package com.example.offerserver.offerservice.task1;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "stuff_table")
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
    @Id
    private UUID id;
    @Column(name="surname")
    private String surname;
    @Column(name="name")
    private String name;
    @Column(name="patronymic")
    private String patronymic;
    @Column(name="sex")
    private boolean sex;
    @Column(name="birth_date")
    private LocalDate birthDate;
    @Column(name="salary_multiplier")
    private Double salaryMultiplier;
    @OneToOne(fetch = FetchType.LAZY)
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stuff stuff = (Stuff)o;

        if (sex != stuff.sex) return false;
        if (!Objects.equals(id, stuff.id)) return false;
        if (!Objects.equals(surname, stuff.surname)) return false;
        if (!Objects.equals(name, stuff.name)) return false;
        if (!Objects.equals(patronymic, stuff.patronymic)) return false;
        if (!Objects.equals(birthDate, stuff.birthDate)) return false;
        if (!Objects.equals(salaryMultiplier, stuff.salaryMultiplier))
            return false;
        return Objects.equals(position, stuff.position);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (sex ? 1 : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (salaryMultiplier != null ? salaryMultiplier.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}

