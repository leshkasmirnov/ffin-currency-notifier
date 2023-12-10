package kz.asmirnov.ffin.ffincurrencynotifier.entity;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import kz.asmirnov.ffin.ffincurrencynotifier.dto.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@MappedEntity
public record RateUpdate(@Id @GeneratedValue @NonNull Long id,
                         @NonNull Currency currencyBuy,
                         @NonNull Currency currencySell,
                         @NonNull BigDecimal lastRate,
                         @NonNull LocalDateTime lastUpdate) {
}
