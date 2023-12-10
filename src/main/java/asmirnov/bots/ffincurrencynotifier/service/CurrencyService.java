package asmirnov.bots.ffincurrencynotifier.service;

import asmirnov.bots.ffincurrencynotifier.dto.CurrencyPair;
import asmirnov.bots.ffincurrencynotifier.dto.RateUpdateDTO;
import asmirnov.bots.ffincurrencynotifier.ffinclient.dto.CurrencyItem;
import asmirnov.bots.ffincurrencynotifier.ffinclient.dto.CurrencyListResponse;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Optional;

@Singleton
public class CurrencyService {

    private final Logger log = LoggerFactory.getLogger(CurrencyService.class);

    private final RatesProvider ratesProvider;
    private final RateUpdateService rateUpdateService;

    public CurrencyService(RatesProvider ratesProvider, RateUpdateService rateUpdateService) {
        this.ratesProvider = ratesProvider;
        this.rateUpdateService = rateUpdateService;
    }

    public BigDecimal getCurrentRate(CurrencyPair pair, Long correction) {
        CurrencyListResponse actualRates = ratesProvider.getActualRates();
        log.info("Got rates: " + actualRates);

        Optional<CurrencyItem> currencyItem = actualRates.data().mobile().stream()
                .filter(data -> pair.currencyBuy().toString().equals(data.buyCode()) && pair.currencySell().toString().equals(data.sellCode()))
                .filter(data -> data.sellRate().compareTo(BigDecimal.valueOf(correction)) > 0)
                .findFirst();

        return currencyItem.map(CurrencyItem::sellRate).orElse(null);
    }

    public Optional<RateUpdateDTO> checkRate(CurrencyPair currencyPair) {
        Optional<RateUpdateDTO> result = Optional.empty();

        Optional<BigDecimal> actualRate = ratesProvider.getActualRate(currencyPair);
        if (actualRate.isEmpty()) {
            return result;
        }
        Optional<RateUpdateDTO> rateUpdate = rateUpdateService.findByCurrencyBuyAndCurrencySell(currencyPair);
        if (rateUpdate.isPresent()) {
            if (rateUpdate.get().lastRate().compareTo(actualRate.get()) != 0) {
                result = Optional.of(rateUpdateService.updateRate(rateUpdate.get(), actualRate.get()));
            }
        } else {
            result = Optional.of(rateUpdateService.saveRate(currencyPair, actualRate.get()));
        }

        return result;
    }

}
