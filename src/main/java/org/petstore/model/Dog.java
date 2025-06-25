package org.petstore.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Dog extends Pet {
    private int rating;

    public Dog(String name, String description, LocalDate dateOfBirth, int rating) {
        super(name, PetType.DOG, description, dateOfBirth);
        this.rating = rating;
    }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal(getAge()).add(new BigDecimal(rating));
    }

    @Override
    public String toString() {
        return super.toString().replace("}", ", rating=" + rating + '}');
    }
}