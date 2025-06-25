package org.petstore.store;

import org.petstore.model.Pet;
import org.petstore.model.User;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    public static final List<User> USERS = new ArrayList<>();
    public static final List<Pet> PETS = new ArrayList<>();
    public static final List<String> HISTORY_LOG = new ArrayList<>();
}