package kz.asmirnov.ffin.ffincurrencynotifier.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RateUpdate(Long id, CurrencyPair currencyPair, BigDecimal lastRate, LocalDateTime lastUpdate) {
}