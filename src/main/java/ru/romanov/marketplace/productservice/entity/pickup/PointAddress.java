package ru.romanov.marketplace.productservice.entity.pickup;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.experimental.FieldDefaults;
import ru.romanov.marketplace.productservice.entity.Address;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class PointAddress extends Address implements Serializable {

    @Serial
    private static final long serialVersionUID = -435785760181632644L;

    String entrance;

}
