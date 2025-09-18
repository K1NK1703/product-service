package ru.romanov.marketplace.productservice.entity.user;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Table(name = "users")
public class User extends BaseUser {

    public User() {
        super.setRole(UserRole.USER);
    }

    public User(String username,
                String email,
                String phone,
                String password,
                String firstName,
                String lastName) {
        super(username, email, phone, password, UserRole.USER, firstName, lastName);
    }
}
