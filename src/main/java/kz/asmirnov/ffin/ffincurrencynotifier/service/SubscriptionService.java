package kz.asmirnov.ffin.ffincurrencynotifier.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import kz.asmirnov.ffin.ffincurrencynotifier.dto.Currency;
import kz.asmirnov.ffin.ffincurrencynotifier.dto.SubscriptionDTO;
import kz.asmirnov.ffin.ffincurrencynotifier.entity.Subscription;
import kz.asmirnov.ffin.ffincurrencynotifier.repository.SubscriptionRepository;

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
