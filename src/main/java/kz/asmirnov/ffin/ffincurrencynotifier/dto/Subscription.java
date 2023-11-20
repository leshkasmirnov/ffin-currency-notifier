package kz.asmirnov.ffin.ffincurrencynotifier.dto;

public record Subscription(Long id, Long chatId, Currency currencyBuy, Currency currencySell) {
}
