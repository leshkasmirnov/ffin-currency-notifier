package kz.asmirnov.ffin.ffincurrencynotifier.ffinclient.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record CurrencyData(List<CurrencyItem> cash, List<CurrencyItem> mobile, List<CurrencyItem> non_cash) {
}
