package kz.asmirnov.ffin.ffincurrencynotifier.repository;

import kz.asmirnov.ffin.ffincurrencynotifier.entity.SubscriptionEntity;

import java.util.List;

public interface SubscriptionRepository {

    void deleteByChatId(Long chatId);

    SubscriptionEntity save(SubscriptionEntity subscriptionEntity);

    List<SubscriptionEntity> findAll();
}
