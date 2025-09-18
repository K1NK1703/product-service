package ru.romanov.marketplace.productservice.entity.delivery;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.romanov.marketplace.productservice.entity.pickup.PointAddress;
import ru.romanov.marketplace.productservice.entity.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@DiscriminatorValue("pickup_point")
public class PickupPointDelivery extends Delivery {

    @Column(name = "pickup_point_address")
    String pickupPointAddress;

    @Builder
    public PickupPointDelivery(LocalDateTime deliveryTime,
                               LocalDateTime createdAt,
                               User createdBy,
                               DeliveryStatus status,
                               PointAddress pickupPointAddress) {
        super(deliveryTime, createdAt, createdBy, status);
        this.pickupPointAddress = pickupPointAddress.toString();
    }
}
