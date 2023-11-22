package kz.asmirnov.ffin.ffincurrencynotifier.repository;

import kz.asmirnov.ffin.ffincurrencynotifier.dto.Currency;
import kz.asmirnov.ffin.ffincurrencynotifier.entity.RateUpdateEntity;

import java.util.Optional;

public interface RateUpdateRepository {

    Optional<RateUpdateEntity> findByCurrencyBuyAndCurrencySell(Currency currencyBuy, Currency currencySell);

    RateUpdateEntity save(RateUpdateEntity entity);

    Optional<RateUpdateEntity> findById(Long id);
}
