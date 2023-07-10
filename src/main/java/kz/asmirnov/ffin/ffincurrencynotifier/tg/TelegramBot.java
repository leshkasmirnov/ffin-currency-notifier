package kz.asmirnov.ffin.ffincurrencynotifier.tg;

import kz.asmirnov.ffin.ffincurrencynotifier.config.BotConfig;
import kz.asmirnov.ffin.ffincurrencynotifier.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(TelegramBot.class);

    private final BotConfig botConfig;

    private final CurrencyService currencyService;

    public TelegramBot(BotConfig botConfig, CurrencyService currencyService) {
        super(botConfig.token());
        this.botConfig = botConfig;
        this.currencyService = currencyService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        log.info("Got message: " + message);

        Long srcChatId = message.getChatId();
        switch (message.getText()) {
            case ("/subscribe") -> currencyService.addListener(srcChatId,
                    (chatId, currentRate) -> sendMessage(chatId, "Current rate is " + currentRate));
            case ("/unsubscribe") -> currencyService.removeListener(srcChatId);
            case ("/get") -> sendMessage(srcChatId, "Current rate is " + currencyService.getCurrentRate());
            default -> sendMessage(srcChatId, "I can't understand you, sorry!");
        }
    }

    private void sendMessage(Long chatId, String textToSend) {
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
