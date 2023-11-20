package kz.asmirnov.ffin.ffincurrencynotifier.ffinclient.dto;

public record CurrencyListResponse(
        boolean success,
        String message,
        CurrencyData data,
        Integer status
) {
}
