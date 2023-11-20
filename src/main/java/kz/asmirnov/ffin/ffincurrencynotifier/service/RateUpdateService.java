package kz.asmirnov.ffin.ffincurrencynotifier.service;

import kz.asmirnov.ffin.ffincurrencynotifier.dto.CurrencyPair;
import kz.asmirnov.ffin.ffincurrencynotifier.dto.RateUpdate;
import kz.asmirnov.ffin.ffincurrencynotifier.entity.RateUpdateEntity;
import kz.asmirnov.ffin.ffincurrencynotifier.repository.RateUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RateUpdateService {

    private final RateUpdateRepository repository;

    @Autowired
    public RateUpdateService(RateUpdateRepository repository) {
        this.repository = repository;
    }

    public Optional<RateUpdate> findByCurrencyBuyAndCurrencySell(CurrencyPair currencyPair) {
        Optional<RateUpdateEntity> rateUpdate =
                repository.findByCurrencyBuyAndCurrencySell(currencyPair.currencyBuy(), currencyPair.currencySell());

        return rateUpdate.map(r ->
                new RateUpdate(r.getId(), new CurrencyPair(r.getCurrencyBuy(), r.getCurrencySell()), r.getLastRate(), r.getLastUpdate())
        );
    }

    public RateUpdate updateRate(RateUpdate rateUpdate, BigDecimal actualRate) {
        Optional<RateUpdateEntity> entity = repository.findById(rateUpdate.id());

        if (entity.isPresent()) {
            RateUpdateEntity rateUpdateEntity = entity.get();
            rateUpdateEntity.setLastRate(actualRate);
            rateUpdateEntity.setLastUpdate(LocalDateTime.now());
            return toDTO(repository.save(rateUpdateEntity));
        } else {
            throw new RuntimeException("Entity with id [" + rateUpdate.id() + "] not found");
        }
    }

    public RateUpdate saveRate(CurrencyPair currencyPair, BigDecimal actualRate) {
        RateUpdateEntity savedEntity = repository.save(new RateUpdateEntity(null,
                currencyPair.currencyBuy(),
                currencyPair.currencySell(),
                actualRate,
                LocalDateTime.now()
        ));

        return toDTO(savedEntity);
    }

    private RateUpdate toDTO(RateUpdateEntity entity) {
        return new RateUpdate(entity.getId(), new CurrencyPair(entity.getCurrencyBuy(), entity.getCurrencySell()),
                entity.getLastRate(), entity.getLastUpdate());
    }
}
