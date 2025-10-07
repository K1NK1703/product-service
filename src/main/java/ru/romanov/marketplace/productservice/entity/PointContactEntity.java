package ru.romanov.marketplace.productservice.entity;

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
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import ru.romanov.marketplace.productservice.entity.pickup.PickupPointEntity;

import java.util.Set;
import java.util.UUID;
import java.util.HashSet;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "point_contacts")
public class PointContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "site")
    private String site;

    @Builder.Default
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PickupPointEntity> pickupPointEntities = new HashSet<>();

    public void addPickupPoint(PickupPointEntity pickupPointEntity) {
        pickupPointEntities.add(pickupPointEntity);
        pickupPointEntity.setContact(this);
    }

    public void removePickupPoint(PickupPointEntity pickupPointEntity) {
        pickupPointEntities.remove(pickupPointEntity);
        pickupPointEntity.setContact(null);
    }
}
