package kz.asmirnov.ffin.ffincurrencynotifier;

import kz.asmirnov.ffin.ffincurrencynotifier.data.Subscription;
import kz.asmirnov.ffin.ffincurrencynotifier.repository.SubscriptionDao;
import kz.asmirnov.ffin.ffincurrencynotifier.tg.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@Profile("!test")
public class BotInitializer {

    private final Logger log = LoggerFactory.getLogger(BotInitializer.class);

    private final TelegramBot telegramBot;

    private final SubscriptionDao subscriptionDao;

    @Autowired
    public BotInitializer(TelegramBot telegramBot, SubscriptionDao subscriptionDao) {
        this.telegramBot = telegramBot;
        this.subscriptionDao = subscriptionDao;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init()throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try{
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e){
            log.error("Error in initializing bot", e);
        }

        for (Subscription s : subscriptionDao.loadAll()) {

        }
    }
}
