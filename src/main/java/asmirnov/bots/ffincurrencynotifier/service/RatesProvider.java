package asmirnov.bots.ffincurrencynotifier.service;

import asmirnov.bots.ffincurrencynotifier.dto.CurrencyPair;
import asmirnov.bots.ffincurrencynotifier.ffinclient.dto.CurrencyListResponse;

import java.math.BigDecimal;
import java.util.Optional;

public interface RatesProvider {
    CurrencyListResponse getActualRates();

    Optional<BigDecimal> getActualRate(CurrencyPair currencyPair);
}
