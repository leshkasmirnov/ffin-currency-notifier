package kz.asmirnov.ffin.ffincurrencynotifier.repository;

import kz.asmirnov.ffin.ffincurrencynotifier.entity.SubscriptionEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends ListCrudRepository<SubscriptionEntity, Long> {

    void deleteByChatId(Long chatId);
}
