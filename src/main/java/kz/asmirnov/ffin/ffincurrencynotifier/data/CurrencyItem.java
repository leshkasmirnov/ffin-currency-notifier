package kz.asmirnov.ffin.ffincurrencynotifier.data;

import java.math.BigDecimal;

public record CurrencyItem(String buyCode, String sellCode, BigDecimal buyRate, BigDecimal sellRate) {
}
