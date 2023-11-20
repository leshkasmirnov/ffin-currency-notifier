package kz.asmirnov.ffin.ffincurrencynotifier.repository;

import kz.asmirnov.ffin.ffincurrencynotifier.dto.Currency;
import kz.asmirnov.ffin.ffincurrencynotifier.entity.RateUpdateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateUpdateRepository extends CrudRepository<RateUpdateEntity, Long> {

    Optional<RateUpdateEntity> findByCurrencyBuyAndCurrencySell(Currency currencyBuy, Currency currencySell);
}
