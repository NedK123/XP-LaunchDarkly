package com.example.xplaunchdarkly.persistence;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class UsersRegistry implements Supplier<List<UserDetails>> {
    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";
    private static final String RM = "RM";
    private static final String PM = "PM";

    @Override
    public List<UserDetails> get() {
        return List.of(
                generateUser("john", USER),
                generateUser("alain", USER),
                generateUser("sundar", PM),
                generateUser("james", PM),
                generateUser("dalal", RM),
                generateUser("chris", RM),
                generateUser("ned", ADMIN)
        );
    }

    private UserDetails generateUser(String username, String... roles) {
        return User.withUsername(username).password(encode(username))
                .roles(roles).build();
    }

    private String encode(String plainTextPassword) {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(plainTextPassword);
    }

}
