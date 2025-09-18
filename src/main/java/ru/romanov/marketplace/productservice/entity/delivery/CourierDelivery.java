package ru.romanov.marketplace.productservice.entity.delivery;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.romanov.marketplace.productservice.entity.user.Courier;
import ru.romanov.marketplace.productservice.entity.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@DiscriminatorValue("courier")
public class CourierDelivery extends Delivery {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
    Courier courier;

    @Column(name = "delivery_address")
    String deliveryAddress;

    @Builder
    public CourierDelivery(LocalDateTime deliveryTime,
                           LocalDateTime createdAt,
                           User createdBy,
                           DeliveryStatus status,
                           Courier courier,
                           DeliveryAddress deliveryAddress) {
        super(deliveryTime, createdAt, createdBy, status);
        this.courier = courier;
        this.deliveryAddress = deliveryAddress.toString();
    }
}
