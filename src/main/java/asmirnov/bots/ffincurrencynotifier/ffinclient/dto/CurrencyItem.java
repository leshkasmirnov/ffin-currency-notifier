package asmirnov.bots.ffincurrencynotifier.ffinclient.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.math.BigDecimal;

@Serdeable
public record CurrencyItem(String buyCode, String sellCode, BigDecimal buyRate, BigDecimal sellRate) {
}
