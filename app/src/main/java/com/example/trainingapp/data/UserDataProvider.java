package com.example.trainingapp.data;

import com.example.trainingapp.data.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataProvider {
    private static UserDataProvider singletonInstance = null;
    private List<User> users;

    private UserDataProvider() {
        User testUser = new User("0", "Oscar A.", "Esquivel Oviedo", "oscar@esquivel.com", "");
        users = new ArrayList<User>();
        users.add(testUser);
    }

    public static UserDataProvider getSingletonInstance() {
        if (singletonInstance == null) {
            synchronized (UserDataProvider.class) {
                singletonInstance = new UserDataProvider();
            }
        }
        return singletonInstance;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserWithID(String userId) {
        for (User user: users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}
