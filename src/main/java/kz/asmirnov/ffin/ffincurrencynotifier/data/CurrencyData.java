package kz.asmirnov.ffin.ffincurrencynotifier.data;

import java.util.List;

public record CurrencyData(List<CurrencyItem> cash, List<CurrencyItem> mobile, List<CurrencyItem> non_cash) {
}
