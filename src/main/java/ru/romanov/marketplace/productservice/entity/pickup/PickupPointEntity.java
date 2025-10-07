package ru.romanov.marketplace.productservice.entity.pickup;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import ru.romanov.marketplace.productservice.entity.PointContactEntity;
import ru.romanov.marketplace.productservice.entity.user.EmployeeEntity;

import java.util.Set;
import java.util.UUID;
import java.util.HashSet;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pickup_points")
public class PickupPointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Embedded
    private PointAddress address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", nullable = false)
    private PointContactEntity contact;

    @Column(name = "rating", precision = 4, scale = 2)
    private BigDecimal rating;

    @Embedded
    private WorkingHours workingHours;

    @Builder.Default
    @OneToMany(mappedBy = "pickupPoint", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeeEntity> employees = new HashSet<>();

    public void addEmployee(EmployeeEntity employee) {
        employees.add(employee);
        employee.setPickupPoint(this);
    }

    public void removeEmployee(EmployeeEntity employee) {
        employees.remove(employee);
        employee.setPickupPoint(null);
    }
}
