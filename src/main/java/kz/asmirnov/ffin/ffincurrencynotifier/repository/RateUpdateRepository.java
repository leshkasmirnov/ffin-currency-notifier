package kz.asmirnov.ffin.ffincurrencynotifier.repository;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import kz.asmirnov.ffin.ffincurrencynotifier.dto.Currency;
import kz.asmirnov.ffin.ffincurrencynotifier.entity.RateUpdate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface RateUpdateRepository extends CrudRepository<RateUpdate, Long> {
    Optional<RateUpdate> findByCurrencyBuyAndCurrencySell(Currency currencyBuy, Currency currencySell);
    RateUpdate save(RateUpdate entity);
    Optional<RateUpdate> findById(Long id);
    int update(@Id Long id, @NonNull BigDecimal lastRate, @NonNull LocalDateTime lastUpdate);
}
