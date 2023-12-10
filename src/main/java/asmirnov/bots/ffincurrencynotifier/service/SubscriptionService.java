package asmirnov.bots.ffincurrencynotifier.service;

import asmirnov.bots.ffincurrencynotifier.dto.Currency;
import asmirnov.bots.ffincurrencynotifier.dto.SubscriptionDTO;
import asmirnov.bots.ffincurrencynotifier.entity.Subscription;
import asmirnov.bots.ffincurrencynotifier.repository.SubscriptionRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class SubscriptionService {

    private final SubscriptionRepository repository;

    @Inject
    public SubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    public void subscribe(Long chatId) {
        repository.save(new Subscription(null, chatId, Currency.EUR, Currency.RUB));
    }

    public void unsubscribe(Long chatId) {
        repository.deleteByChatId(chatId);
    }

    public List<SubscriptionDTO> getAllSubscriptions() {
        return repository.findAll().stream()
                .map(s -> new SubscriptionDTO(s.id(), s.chatId(), s.currencyBuy(), s.currencySell()))
                .toList();
    }
}
