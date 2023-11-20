package kz.asmirnov.ffin.ffincurrencynotifier.service;

import kz.asmirnov.ffin.ffincurrencynotifier.dto.Currency;
import kz.asmirnov.ffin.ffincurrencynotifier.dto.Subscription;
import kz.asmirnov.ffin.ffincurrencynotifier.entity.SubscriptionEntity;
import kz.asmirnov.ffin.ffincurrencynotifier.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository repository;

    @Autowired
    public SubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    public void subscribe(Long chatId) {
        repository.save(new SubscriptionEntity(null, chatId, Currency.EUR, Currency.RUB));
    }

    public void unsubscribe(Long chatId) {
        repository.deleteByChatId(chatId);
    }

    public List<Subscription> getAllSubscriptions() {
        return repository.findAll().stream()
                .map(s -> new Subscription(s.getId(), s.getChatId(), s.getCurrencyBuy(), s.getCurrencySell()))
                .toList();
    }
}
