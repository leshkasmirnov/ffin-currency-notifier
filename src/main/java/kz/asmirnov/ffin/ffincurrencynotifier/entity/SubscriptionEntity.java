package kz.asmirnov.ffin.ffincurrencynotifier.entity;

import kz.asmirnov.ffin.ffincurrencynotifier.dto.Currency;

import java.util.Objects;

public class SubscriptionEntity {

    private Long id;
    Long chatId;
    Currency currencyBuy;
    Currency currencySell;

    public SubscriptionEntity(Long id, Long chatId, Currency currencyBuy, Currency currencySell) {
        this.id = id;
        this.chatId = chatId;
        this.currencyBuy = currencyBuy;
        this.currencySell = currencySell;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
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

    @Override
    public String toString() {
        return "SubscriptionEntity{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", currencyBuy=" + currencyBuy +
                ", currencySell=" + currencySell +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionEntity that = (SubscriptionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(chatId, that.chatId) && currencyBuy == that.currencyBuy && currencySell == that.currencySell;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, currencyBuy, currencySell);
    }
}
