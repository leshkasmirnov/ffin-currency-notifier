package asmirnov.bots.ffincurrencynotifier.service;

import asmirnov.bots.ffincurrencynotifier.dto.CurrencyPair;
import asmirnov.bots.ffincurrencynotifier.dto.RateUpdateDTO;
import asmirnov.bots.ffincurrencynotifier.entity.RateUpdate;
import asmirnov.bots.ffincurrencynotifier.repository.RateUpdateRepository;
import jakarta.inject.Singleton;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Singleton
public class RateUpdateService {

    private final RateUpdateRepository repository;

    public RateUpdateService(RateUpdateRepository repository) {
        this.repository = repository;
    }

    public Optional<RateUpdateDTO> findByCurrencyBuyAndCurrencySell(CurrencyPair currencyPair) {
        Optional<RateUpdate> rateUpdate =
                repository.findByCurrencyBuyAndCurrencySell(currencyPair.currencyBuy(), currencyPair.currencySell());

        return rateUpdate.map(r ->
                new RateUpdateDTO(r.id(), new CurrencyPair(r.currencyBuy(), r.currencySell()), r.lastRate(), r.lastUpdate())
        );
    }

    public RateUpdateDTO updateRate(RateUpdateDTO rateUpdate, BigDecimal actualRate) {
        Optional<RateUpdate> entity = repository.findById(rateUpdate.id());

        if (entity.isPresent()) {
            RateUpdate fromDb = entity.get();
            int rowsCount = repository.update(fromDb.id(), actualRate, LocalDateTime.now());
            if (rowsCount > 0) {
                return new RateUpdateDTO(fromDb.id(), new CurrencyPair(fromDb.currencyBuy(), fromDb.currencySell()), actualRate, LocalDateTime.now());
            }
            return rateUpdate;
        } else {
            throw new RuntimeException("Entity with id [" + rateUpdate.id() + "] not found");
        }
    }

    public RateUpdateDTO saveRate(CurrencyPair currencyPair, BigDecimal actualRate) {
        RateUpdate savedEntity = repository.save(new RateUpdate(null,
                currencyPair.currencyBuy(),
                currencyPair.currencySell(),
                actualRate,
                LocalDateTime.now()
        ));

        return toDTO(savedEntity);
    }

    private RateUpdateDTO toDTO(RateUpdate entity) {
        return new RateUpdateDTO(entity.id(), new CurrencyPair(entity.currencyBuy(), entity.currencySell()),
                entity.lastRate(), entity.lastUpdate());
    }
}
