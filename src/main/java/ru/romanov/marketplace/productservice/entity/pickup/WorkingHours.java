package ru.romanov.marketplace.productservice.entity.pickup;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class WorkingHours {
    private String openTime;
    private String closeTime;
}
