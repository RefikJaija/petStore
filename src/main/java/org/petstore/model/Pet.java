package org.petstore.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public abstract class Pet {
    private String name;
    private PetType type;
    private String description;
    private LocalDate dateOfBirth;
    private User owner;

    public Pet(String name, PetType type, String description, LocalDate dateOfBirth) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
    }

    public abstract BigDecimal getPrice();

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.of(2022, 7, 1)).getYears();
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public PetType getType() { return type; }
    public void setType(PetType type) { this.type = type; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    @Override
    public String toString() {
        String ownerName = (owner != null) ? owner.getFirstName() + " " + owner.getLastName() : "No Owner";
        return "Pet{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", price=" + getPrice() +
                ", owner=" + ownerName +
                '}';
    }
}