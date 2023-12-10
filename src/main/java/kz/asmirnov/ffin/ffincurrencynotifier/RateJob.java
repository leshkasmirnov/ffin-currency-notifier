package kz.asmirnov.ffin.ffincurrencynotifier;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import kz.asmirnov.ffin.ffincurrencynotifier.dto.Currency;
import kz.asmirnov.ffin.ffincurrencynotifier.dto.CurrencyPair;
import kz.asmirnov.ffin.ffincurrencynotifier.dto.SubscriptionDTO;
import kz.asmirnov.ffin.ffincurrencynotifier.service.CurrencyService;
import kz.asmirnov.ffin.ffincurrencynotifier.service.SubscriptionService;
import kz.asmirnov.ffin.ffincurrencynotifier.bot.TgBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class RateJob {

    private final Logger log = LoggerFactory.getLogger(RateJob.class);

    private final CurrencyService currencyService;
    private final SubscriptionService subscriptionService;
    private final TgBot tgBot;

    public RateJob(CurrencyService currencyService, SubscriptionService subscriptionService, TgBot tgBot) {
        this.currencyService = currencyService;
        this.subscriptionService = subscriptionService;
        this.tgBot = tgBot;
    }

    @Scheduled(cron = "${update-cron}")
    public void checkRate() {
        final List<SubscriptionDTO> subscriptions = subscriptionService.getAllSubscriptions();
        if (subscriptions.isEmpty()) {
            log.info("Listeners is empty. There is no need to update rate.");
            return;
        }

        currencyService.checkRate(new CurrencyPair(Currency.EUR, Currency.RUB)).ifPresent(r ->
                subscriptions.forEach(s -> tgBot.sendMessage(s.chatId(), TgBot.CURRENT_RATE_MESSAGE.formatted(r.lastRate())))
        );
    }
}
