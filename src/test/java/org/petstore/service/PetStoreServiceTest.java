package org.petstore.service;

import org.petstore.model.Cat;
import org.petstore.model.Dog;
import org.petstore.model.User;
import org.petstore.store.DataStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class PetStoreServiceTest {

    private PetStoreService petStoreService;

    @BeforeEach
    void setUp() {
        petStoreService = new PetStoreService();
        DataStore.USERS.clear();
        DataStore.PETS.clear();
        DataStore.HISTORY_LOG.clear();
    }

    @Test
    void testCreateUsers() {
        petStoreService.createUsers();
        assertEquals(10, DataStore.USERS.size());
    }

    @Test
    void testCreatePets() {
        petStoreService.createPets();
        assertEquals(20, DataStore.PETS.size());
    }

    @Test
    void testBuyPetSuccess() {
        User user = new User("Test", "User", "test@user.com", new BigDecimal("100"));
        DataStore.USERS.add(user);
        Dog dog = new Dog("TestDog", "A test dog", LocalDate.of(2018, 1, 1), 3);
        DataStore.PETS.add(dog); // Price should be (4 * 1) + 3 = 7

        petStoreService.buyPet();

        assertEquals(dog.getOwner(), user);
        assertEquals(new BigDecimal("93"), user.getBudget());
        assertEquals(1, DataStore.HISTORY_LOG.size());
    }

    @Test
    void testBuyPetInsufficientFunds() {
        User user = new User("Poor", "User", "poor@user.com", new BigDecimal("5"));
        DataStore.USERS.add(user);
        Dog dog = new Dog("TestDog", "A test dog", LocalDate.of(2018, 1, 1), 3);
        DataStore.PETS.add(dog); // Price is 7

        petStoreService.buyPet();

        assertNull(dog.getOwner());
        assertEquals(new BigDecimal("5"), user.getBudget());
        assertEquals(1, DataStore.HISTORY_LOG.size());
    }

    @Test
    void testCatPriceCalculation() {
        Cat cat = new Cat("Whiskers", "A cute cat", LocalDate.of(2020, 7, 1));
        // Age is 2 years as of 2022-07-01
        assertEquals(new BigDecimal("2"), cat.getPrice());
    }

    @Test
    void testDogPriceCalculation() {
        Dog dog = new Dog("Buddy", "A friendly dog", LocalDate.of(2018, 7, 1), 5);
        // Age is 4 years as of 2022-07-01, rating is 5. Price = 4 + 5 = 9
        assertEquals(new BigDecimal("9"), dog.getPrice());
    }
}