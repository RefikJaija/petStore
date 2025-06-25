package org.petstore.service;

import org.petstore.model.*;
import org.petstore.store.DataStore;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PetStoreService {

    private static final String[] FIRST_NAMES = {"John", "Jane", "Peter", "Susan", "Michael", "Linda"};
    private static final String[] LAST_NAMES = {"Doe", "Smith", "Jones", "Williams", "Brown", "Davis"};
    private static final String[] PET_NAMES = {"Buddy", "Lucy", "Max", "Bella", "Charlie", "Molly"};
    private static final String[] CAT_DESCRIPTIONS = {"A fluffy friend", "Loves to nap", "Very playful"};
    private static final String[] DOG_DESCRIPTIONS = {"A loyal companion", "Full of energy", "Great with kids"};
    private static final Random RANDOM = new Random();

    public void createUsers() {
        for (int i = 0; i < 10; i++) {
            String firstName = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
            String lastName = LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + i + "@example.com";
            BigDecimal budget = new BigDecimal(RANDOM.nextInt(100) + 20);
            DataStore.USERS.add(new User(firstName, lastName, email, budget));
        }
        System.out.println("10 users created successfully.");
    }

    public void createPets() {
        for (int i = 0; i < 20; i++) {
            PetType type = PetType.values()[RANDOM.nextInt(PetType.values().length)];
            String name = PET_NAMES[RANDOM.nextInt(PET_NAMES.length)];
            LocalDate dob = LocalDate.of(2018 + RANDOM.nextInt(4), RANDOM.nextInt(12) + 1, RANDOM.nextInt(28) + 1);

            if (type == PetType.CAT) {
                String description = CAT_DESCRIPTIONS[RANDOM.nextInt(CAT_DESCRIPTIONS.length)];
                DataStore.PETS.add(new Cat(name, description, dob));
            } else {
                String description = DOG_DESCRIPTIONS[RANDOM.nextInt(DOG_DESCRIPTIONS.length)];
                int rating = RANDOM.nextInt(11);
                DataStore.PETS.add(new Dog(name, description, dob, rating));
            }
        }
        System.out.println("20 pets created successfully.");
    }

    public void buyPet() {
        int successfulBuys = 0;
        int failedBuys = 0;

        for (User user : DataStore.USERS) {
            boolean boughtPet = false;
            for (Pet pet : DataStore.PETS) {
                if (pet.getOwner() == null && user.getBudget().compareTo(pet.getPrice()) >= 0) {
                    user.setBudget(user.getBudget().subtract(pet.getPrice()));
                    pet.setOwner(user);
                    successfulBuys++;
                    if (pet instanceof Cat) {
                        System.out.println("Meow, cat " + pet.getName() + " has owner " + user.getFirstName() + " " + user.getLastName());
                    } else if (pet instanceof Dog) {
                        System.out.println("Woof, dog " + pet.getName() + " has owner " + user.getFirstName() + " " + user.getLastName());
                    }
                    boughtPet = true;
                    break;
                }
            }
            if (!boughtPet) {
                failedBuys++;
            }
        }
        String logEntry = String.format("Date of execution: %s, Successful buys: %d, Failed buys: %d",
                LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE), successfulBuys, failedBuys);
        DataStore.HISTORY_LOG.add(logEntry);
        System.out.println("Buy command executed. See log for details.");
    }
}