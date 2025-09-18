package ru.romanov.marketplace.productservice.entity.pickup;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "point_contacts_info")
public class PointContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String phoneNumber;

    @Column
    String site;

    @Builder.Default
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    List<PickupPoint> pickupPoints = new ArrayList<>();

    public void addPickupPoint(PickupPoint pickupPoint) {
        pickupPoints.add(pickupPoint);
        pickupPoint.setContact(this);
    }

    public void removePickupPoint(PickupPoint pickupPoint) {
        pickupPoints.remove(pickupPoint);
        pickupPoint.setContact(null);
    }
}
