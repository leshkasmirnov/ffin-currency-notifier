package kz.asmirnov.ffin.ffincurrencynotifier.entity;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import kz.asmirnov.ffin.ffincurrencynotifier.dto.Currency;

@MappedEntity
public record Subscription(
        @Id @GeneratedValue @NonNull Long id,
        @NonNull Long chatId,
        @NonNull Currency currencyBuy,
        @NonNull Currency currencySell) {
}
