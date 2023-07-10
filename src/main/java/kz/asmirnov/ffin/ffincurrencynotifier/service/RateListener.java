package kz.asmirnov.ffin.ffincurrencynotifier.service;

import java.math.BigDecimal;

@FunctionalInterface
public interface RateListener {

    void updated(long chatId, BigDecimal currentRate);
}
