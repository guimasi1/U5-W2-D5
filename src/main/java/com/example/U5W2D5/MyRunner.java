package com.example.U5W2D5;

import com.example.U5W2D5.user.User;
import com.example.U5W2D5.user.UsersService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    UsersService usersService;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello");
        // createFakeUsers();
    }

    public void createFakeUsers() {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = faker.internet().emailAddress();
            String avatarUrl = "https://ui-avatars.com/api/?name=" + firstName + "+" + lastName;

            User user = new User(faker.name().username(),firstName,lastName,email,
                    avatarUrl);
            usersService.save(user);
        }
    }
}
