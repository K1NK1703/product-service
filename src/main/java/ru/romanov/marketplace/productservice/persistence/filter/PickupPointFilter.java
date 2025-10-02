package ru.romanov.marketplace.productservice.persistence.filter;

import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import ru.romanov.marketplace.productservice.entity.pickup.PointAddress;

import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor
public class PickupPointFilter {

    private UUID id;

    private PointAddress address;

    private PickupPointFilter(UUID id, PointAddress address) {
        this.id = id;
        this.address = address;
    }

    public static PickupPointFilter byId(UUID id) {
        return new PickupPointFilter(id, null);
    }

    public static PickupPointFilter byAddress(PointAddress address) {
        return new PickupPointFilter(null, address);
    }

    public PickupPointFilter setPickupPointId(UUID id) {
        this.id = id;
        return this;
    }

    public PickupPointFilter setAddress(PointAddress address) {
        this.address = address;
        return this;
    }

    public boolean isEmpty() {
        return id == null && address == null;
    }
}
