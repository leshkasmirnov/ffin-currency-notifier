package kz.asmirnov.ffin.ffincurrencynotifier.service;

import kz.asmirnov.ffin.ffincurrencynotifier.dto.CurrencyPair;
import kz.asmirnov.ffin.ffincurrencynotifier.ffinclient.FreedomFinanceClient;
import kz.asmirnov.ffin.ffincurrencynotifier.ffinclient.dto.CurrencyItem;
import kz.asmirnov.ffin.ffincurrencynotifier.ffinclient.dto.CurrencyListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class FreedomFinanceRatesProvider implements RatesProvider {

    private final FreedomFinanceClient client;

    @Autowired
    public FreedomFinanceRatesProvider(FreedomFinanceClient client) {
        this.client = client;
    }

    @Override
    public CurrencyListResponse getActualRates() {
        return client.getActualRates();
    }

    @Override
    public Optional<BigDecimal> getActualRate(CurrencyPair currencyPair) {
        Optional<CurrencyItem> currencyItem = getActualRates().data().mobile().stream()
                .filter(data -> currencyPair.currencyBuy().toString().equals(data.buyCode()) && currencyPair.currencySell().toString().equals(data.sellCode()))
                .findFirst();

        if (currencyItem.isPresent() &&
                currencyItem.get().sellRate() != null) {
            return Optional.of(currencyItem.get().sellRate());
        }

        return Optional.empty();
    }
}