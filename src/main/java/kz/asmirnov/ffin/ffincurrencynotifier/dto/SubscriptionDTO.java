package kz.asmirnov.ffin.ffincurrencynotifier.dto;

public record SubscriptionDTO(Long id, Long chatId, Currency currencyBuy, Currency currencySell) {
}
