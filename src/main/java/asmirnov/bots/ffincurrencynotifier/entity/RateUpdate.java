package asmirnov.bots.ffincurrencynotifier.entity;

import asmirnov.bots.ffincurrencynotifier.dto.Currency;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@MappedEntity
public record RateUpdate(@Id @GeneratedValue @NonNull Long id,
                         @NonNull Currency currencyBuy,
                         @NonNull Currency currencySell,
                         @NonNull BigDecimal lastRate,
                         @NonNull LocalDateTime lastUpdate) {
}
