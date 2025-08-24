package org.example.entity;

import java.time.LocalDate;

public abstract class Person {
    private String name;
    private LocalDate dateOfBirth;

    public Person(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isBlank() || name.length() < 3) {
            throw new EntityException("Name cannot be blank, and must have at least 3 characters");
        }

        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        LocalDate future = LocalDate.now().plusYears(120);
        LocalDate past = LocalDate.now().minusYears(120);

        if (dateOfBirth.isAfter(future) || dateOfBirth.isBefore(past)) {
            throw new EntityException("Date of birth have to be between " + future + " and " + past);
        }

        this.dateOfBirth = dateOfBirth;
    }
}
