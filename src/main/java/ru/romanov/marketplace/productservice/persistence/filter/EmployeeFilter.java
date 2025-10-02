package ru.romanov.marketplace.productservice.persistence.filter;

import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor
public class EmployeeFilter {

    private String role;

    private String username;

    private UUID pickupPointId;

    private EmployeeFilter(String role, String username, UUID pickupPointId) {
        this.role = role;
        this.username = username;
        this.pickupPointId = pickupPointId;
    }

    public static EmployeeFilter byRole(String role) {
        return new EmployeeFilter(role, null, null);
    }

    public static EmployeeFilter byUsername(String username) {
        return new EmployeeFilter(username, null, null);
    }

    public static EmployeeFilter byPickupPointId(UUID pickupPointId) {
        return new EmployeeFilter(null, null, pickupPointId);
    }

    public EmployeeFilter setRole(String role) {
        this.role = role;
        return this;
    }

    public EmployeeFilter setUsername(String username) {
        this.username = username;
        return this;
    }

    public EmployeeFilter setPickupPointId(UUID pickupPointId) {
        this.pickupPointId = pickupPointId;
        return this;
    }

    public boolean isEmpty() {
        return role == null && username == null && pickupPointId == null;
    }
}
