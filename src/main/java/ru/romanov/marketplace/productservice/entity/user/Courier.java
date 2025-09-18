package ru.romanov.marketplace.productservice.entity.user;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import ru.romanov.marketplace.productservice.entity.delivery.CourierDelivery;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "couriers")
public class Courier extends BaseUser {

    @Column(name = "vehicle_type")
    String vehicleType;

    @Builder.Default
    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CourierDelivery> deliveries = new ArrayList<>();

    public Courier() {
        super.setRole(UserRole.COURIER);
    }

    public Courier(String username,
                   String email,
                   String phone,
                   String password,
                   String firstName,
                   String lastName,
                   String vehicleType) {
        super(username, email, phone, password, UserRole.COURIER, firstName, lastName);
        this.vehicleType = vehicleType;
    }

    public void addDelivery(CourierDelivery delivery) {
        deliveries.add(delivery);
        delivery.setCourier(this);
    }

    public void removeDelivery(CourierDelivery delivery) {
        deliveries.remove(delivery);
        delivery.setCourier(null);
    }
}
