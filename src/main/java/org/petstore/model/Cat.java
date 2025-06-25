package org.petstore.model;


import java.math.BigDecimal;
import java.time.LocalDate;

public class Cat extends Pet {

    public Cat(String name, String description, LocalDate dateOfBirth) {
        super(name, PetType.CAT, description, dateOfBirth);
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal(getAge());
    }
}
