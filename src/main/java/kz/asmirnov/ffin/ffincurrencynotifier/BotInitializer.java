package kz.asmirnov.ffin.ffincurrencynotifier;

import kz.asmirnov.ffin.ffincurrencynotifier.tg.TgBot;
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

    private final TgBot tgBot;

    @Autowired
    public BotInitializer(TgBot tgBot) {
        this.tgBot = tgBot;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(tgBot);
        } catch (TelegramApiException e) {
            log.error("Error in initializing bot", e);
        }
    }
}
