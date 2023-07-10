package kz.asmirnov.ffin.ffincurrencynotifier.data;

public record CurrencyListResponse(
        boolean success,
        String message,
        CurrencyData data,
        Integer status
) {
}
