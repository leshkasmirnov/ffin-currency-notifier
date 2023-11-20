package kz.asmirnov.ffin.ffincurrencynotifier.entity;

import jakarta.persistence.*;
import kz.asmirnov.ffin.ffincurrencynotifier.dto.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "rate_update")
public class RateUpdateEntity {
    @Id
    @GeneratedValue(generator = "rate_update_generator")
    @SequenceGenerator(name = "rate_update_generator", sequenceName = "rate_update_id_seq", allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Currency currencyBuy;
    @Enumerated(EnumType.STRING)
    private Currency currencySell;
    private BigDecimal lastRate;
    private LocalDateTime lastUpdate;

    public RateUpdateEntity() {
    }

    public RateUpdateEntity(Long id, Currency currencyBuy, Currency currencySell, BigDecimal lastRate, LocalDateTime lastUpdate) {
        this.id = id;
        this.currencyBuy = currencyBuy;
        this.currencySell = currencySell;
        this.lastRate = lastRate;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Currency getCurrencyBuy() {
        return currencyBuy;
    }

    public void setCurrencyBuy(Currency currencyBuy) {
        this.currencyBuy = currencyBuy;
    }

    public Currency getCurrencySell() {
        return currencySell;
    }

    public void setCurrencySell(Currency currencySell) {
        this.currencySell = currencySell;
    }

    public BigDecimal getLastRate() {
        return lastRate;
    }

    public void setLastRate(BigDecimal lastRate) {
        this.lastRate = lastRate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "RateUpdateEntity{" +
                "id=" + id +
                ", currencyBuy=" + currencyBuy +
                ", currencySell=" + currencySell +
                ", lastRate=" + lastRate +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateUpdateEntity that = (RateUpdateEntity) o;
        return Objects.equals(id, that.id) && currencyBuy == that.currencyBuy && currencySell == that.currencySell && Objects.equals(lastRate, that.lastRate) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currencyBuy, currencySell, lastRate, lastUpdate);
    }
}
