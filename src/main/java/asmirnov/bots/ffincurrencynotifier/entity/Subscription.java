package asmirnov.bots.ffincurrencynotifier.entity;

import asmirnov.bots.ffincurrencynotifier.dto.Currency;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

@MappedEntity
public record Subscription(
        @Id @GeneratedValue @NonNull Long id,
        @NonNull Long chatId,
        @NonNull Currency currencyBuy,
        @NonNull Currency currencySell) {
}
