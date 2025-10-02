package ru.romanov.marketplace.productservice.entity.user;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.romanov.marketplace.productservice.entity.pickup.PickupPointEntity;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "employees")
public class EmployeeEntity extends BaseUser {

    @Column(name = "salary")
    private BigDecimal salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_point_id")
    private PickupPointEntity pickupPoint;

    public EmployeeEntity() {
        super.setRole(UserRole.EMPLOYEE);
    }

    public EmployeeEntity(String username,
                          String email,
                          String phone,
                          String password,
                          String firstName,
                          String lastName,
                          BigDecimal salary) {
        super(username, email, phone, password, UserRole.EMPLOYEE, firstName, lastName);
        this.salary = salary;
    }

    public void assignToPickupPoint(PickupPointEntity pickupPoint) {
        this.pickupPoint = pickupPoint;
        if (pickupPoint != null) {
            pickupPoint.getEmployees().add(this);
        }
    }
}
