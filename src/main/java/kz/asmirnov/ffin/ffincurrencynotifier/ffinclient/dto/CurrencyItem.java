package kz.asmirnov.ffin.ffincurrencynotifier.ffinclient.dto;

import java.math.BigDecimal;

public record CurrencyItem(String buyCode, String sellCode, BigDecimal buyRate, BigDecimal sellRate) {
}
