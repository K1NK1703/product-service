package ru.romanov.marketplace.productservice.entity.delivery;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Inheritance;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.romanov.marketplace.productservice.entity.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "delivery")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "delivery_time", nullable = false)
    LocalDateTime deliveryTime;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id", nullable = false)
    User createdBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    DeliveryStatus status;

    public Delivery(LocalDateTime deliveryTime,
                    LocalDateTime createdAt,
                    User createdBy,
                    DeliveryStatus status) {
        this.deliveryTime = deliveryTime;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.status = status;
    }
}
