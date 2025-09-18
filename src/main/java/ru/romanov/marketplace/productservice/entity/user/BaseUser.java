package ru.romanov.marketplace.productservice.entity.user;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String username;

    @Column(unique = true, nullable = false)
    String email;

    @Column(unique = true, nullable = false)
    String phone;

    @Column(nullable = false)
    String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    UserRole role;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    protected BaseUser(String username,
                       String email,
                       String phone,
                       String password,
                       UserRole role,
                       String firstName,
                       String lastName) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
