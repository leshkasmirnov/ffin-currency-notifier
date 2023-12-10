package asmirnov.bots.ffincurrencynotifier.bot;

import io.micronaut.context.annotation.Context;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Context
public class BotInitializer {

    private final Logger log = LoggerFactory.getLogger(BotInitializer.class);

    private final TgBot tgBot;

    public BotInitializer(TgBot tgBot) {
        this.tgBot = tgBot;
    }

    @PostConstruct
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(tgBot);
        } catch (TelegramApiException e) {
            log.error("Error in initializing bot", e);
        }
    }
}
