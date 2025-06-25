package org.petstore.command;

import org.petstore.model.Cat;
import org.petstore.model.Dog;
import org.petstore.model.User;
import org.petstore.service.PetStoreService;
import org.petstore.store.DataStore;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommandHandler {
    private final PetStoreService petStoreService = new PetStoreService();

    public void handle(String command) {
        String[] parts = command.split(":", 2);
        String mainCommand = parts[0].trim();

        switch (mainCommand) {
            case "create-users":
                petStoreService.createUsers();
                break;
            case "create-pets":
                petStoreService.createPets();
                break;
            case "list-users":
                DataStore.USERS.forEach(System.out::println);
                break;
            case "list-pets":
                DataStore.PETS.forEach(System.out::println);
                break;
            case "buy":
                petStoreService.buyPet();
                break;
            case "history-log":
                DataStore.HISTORY_LOG.forEach(System.out::println);
                break;
            case "user":
                createUser(parts[1].trim());
                break;
            case "pet":
                createPet(parts[1].trim());
                break;
            case "exit":
                System.out.println("Exiting application.");
                System.exit(0);
                break;
            default:
                System.out.println("Unknown command.");
        }
    }

    private void createUser(String data) {
        try {
            String[] parts = data.split(",");
            String firstName = parts[0].trim();
            String lastName = parts[1].trim();
            String email = parts[2].trim();
            BigDecimal budget = new BigDecimal(parts[3].trim());
            DataStore.USERS.add(new User(firstName, lastName, email, budget));
            System.out.println("User created successfully.");
        } catch (Exception e) {
            System.out.println("Invalid format for creating a user. Use: user: <FirstName>, <LastName>, <email@address.com>, <budget>");
        }
    }

    private void createPet(String data) {
        try {
            String[] parts = data.split(",");
            String name = parts[0].trim();
            String type = parts[1].trim();
            String description = parts[2].trim();
            LocalDate dob = LocalDate.parse(parts[3].trim(), DateTimeFormatter.ISO_LOCAL_DATE);

            if ("Cat".equalsIgnoreCase(type)) {
                DataStore.PETS.add(new Cat(name, description, dob));
                System.out.println("Cat created successfully.");
            } else if ("Dog".equalsIgnoreCase(type)) {
                int rating = Integer.parseInt(parts[4].trim());
                DataStore.PETS.add(new Dog(name, description, dob, rating));
                System.out.println("Dog created successfully.");
            } else {
                System.out.println("Invalid pet type. Use 'Cat' or 'Dog'.");
            }
        } catch (Exception e) {
            System.out.println("Invalid format for creating a pet. Use: pet: <Name>, <Type>, <Description>, <YYYY-MM-DD>[, <rating for Dogs>]");
        }
    }
}