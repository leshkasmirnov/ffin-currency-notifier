package kz.asmirnov.ffin.ffincurrencynotifier.job;

import kz.asmirnov.ffin.ffincurrencynotifier.service.CurrencyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RateJob {

    private final CurrencyService currencyService;

    public RateJob(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Scheduled(cron = "${update-cron}")
    public void refreshRate() {
        currencyService.updateRate();
    }
}
