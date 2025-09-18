package ru.romanov.marketplace.productservice.entity.delivery;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.romanov.marketplace.productservice.entity.Address;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class DeliveryAddress extends Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 4162537969275710893L;

    String entrance;
    String floor;
    String apartment;

}
