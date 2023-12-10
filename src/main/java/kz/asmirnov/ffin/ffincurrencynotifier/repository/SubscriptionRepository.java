package kz.asmirnov.ffin.ffincurrencynotifier.repository;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import kz.asmirnov.ffin.ffincurrencynotifier.entity.Subscription;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

    void deleteByChatId(@NonNull Long chatId);

    Subscription save(@NonNull Subscription subscriptionEntity);

    List<Subscription> findAll();
}
