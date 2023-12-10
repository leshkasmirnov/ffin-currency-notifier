package kz.asmirnov.ffin.ffincurrencynotifier.ffinclient.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CurrencyListResponse(
        boolean success,
        String message,
        CurrencyData data,
        Integer status
) {
}
