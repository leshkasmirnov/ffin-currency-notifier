package kz.asmirnov.ffin.ffincurrencynotifier.service;

import kz.asmirnov.ffin.ffincurrencynotifier.data.CurrencyItem;
import kz.asmirnov.ffin.ffincurrencynotifier.data.CurrencyListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CurrencyService {

    private final Logger log = LoggerFactory.getLogger(CurrencyService.class);

    private BigDecimal currentRate = null;

    private final FFinService fFinService;

    private final Map<Long, RateListener> listeners = new HashMap<>();

    public CurrencyService(FFinService fFinService) {
        this.fFinService = fFinService;
    }

    public void addListener(long chatId, RateListener rateListener) {
        listeners.put(chatId, rateListener);
        log.info("Added listener with chatId: " + chatId);
    }

    public BigDecimal getCurrentRate() {
        CurrencyListResponse actualRates = fFinService.getActualRates();
        log.info("Got rates: " + actualRates);

        Optional<CurrencyItem> currencyItem = actualRates.data().mobile().stream()
                .filter(data -> "EUR".equals(data.buyCode()) && "RUB".equals(data.sellCode()))
                .findFirst();

        return currencyItem.map(CurrencyItem::sellRate).orElse(null);
    }

    public void updateRate() {
        if (listeners.isEmpty()) {
            log.info("Listeners is empty. There is no need to update rate.");
            return;
        }

        CurrencyListResponse actualRates = fFinService.getActualRates();
        log.info("Got rates: " + actualRates);

        Optional<CurrencyItem> currencyItem = actualRates.data().mobile().stream()
                .filter(data -> "EUR".equals(data.buyCode()) && "RUB".equals(data.sellCode()))
                .findFirst();

        if (currencyItem.isPresent() &&
                currencyItem.get().sellRate() != null) {

            boolean needNotify = false;
            BigDecimal sellRate = currencyItem.get().sellRate();
            if (currentRate == null) {
                currentRate = sellRate;
                needNotify = true;
            } else if (!sellRate.equals(currentRate)) {
                currentRate = sellRate;
                needNotify = true;
            }

            if (needNotify) {
                // notify
                log.info("Rates changes! Notify listeners: " + listeners);
                listeners.forEach((key, value) -> value.updated(key, currentRate));
            }
        }
    }

    public void removeListener(Long srcChatId) {
        RateListener listener = listeners.remove(srcChatId);
        log.info("Removed listener: " + listener);
    }
}
