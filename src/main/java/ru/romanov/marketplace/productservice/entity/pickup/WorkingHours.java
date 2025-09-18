package ru.romanov.marketplace.productservice.entity.pickup;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class WorkingHours {
    String openTime;
    String closeTime;
}
