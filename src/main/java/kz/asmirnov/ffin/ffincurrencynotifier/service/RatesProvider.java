package kz.asmirnov.ffin.ffincurrencynotifier.service;

import kz.asmirnov.ffin.ffincurrencynotifier.dto.CurrencyPair;
import kz.asmirnov.ffin.ffincurrencynotifier.ffinclient.dto.CurrencyListResponse;

import java.math.BigDecimal;
import java.util.Optional;

public interface RatesProvider {
    CurrencyListResponse getActualRates();

    Optional<BigDecimal> getActualRate(CurrencyPair currencyPair);
}
