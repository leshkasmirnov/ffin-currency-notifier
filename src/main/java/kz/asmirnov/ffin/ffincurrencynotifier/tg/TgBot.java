package kz.asmirnov.ffin.ffincurrencynotifier.tg;

import kz.asmirnov.ffin.ffincurrencynotifier.config.BotConfig;
import kz.asmirnov.ffin.ffincurrencynotifier.dto.Currency;
import kz.asmirnov.ffin.ffincurrencynotifier.dto.CurrencyPair;
import kz.asmirnov.ffin.ffincurrencynotifier.service.CurrencyService;
import kz.asmirnov.ffin.ffincurrencynotifier.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TgBot extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(TgBot.class);

    public static final String CURRENT_RATE_MESSAGE = "Current rate is %s";

    private final BotConfig botConfig;

    private final CurrencyService currencyService;

    private final SubscriptionService subscriptionService;

    public TgBot(BotConfig botConfig, CurrencyService currencyService, SubscriptionService subscriptionService) {
        super(botConfig.token());
        this.botConfig = botConfig;
        this.currencyService = currencyService;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        log.info("Got message: " + message);

        Long srcChatId = message.getChatId();
        switch (message.getText()) {
            case ("/subscribe") -> subscriptionService.subscribe(srcChatId);
            case ("/unsubscribe") -> subscriptionService.unsubscribe(srcChatId);
            case ("/get") -> sendMessage(srcChatId,
                    CURRENT_RATE_MESSAGE.formatted(currencyService.getCurrentRate(new CurrencyPair(Currency.EUR, Currency.RUB), 80L))
            );
            default -> sendMessage(srcChatId, "I can't understand you, sorry!");
        }
    }

    public void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Failed to send message", e);
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.name();
    }
}
