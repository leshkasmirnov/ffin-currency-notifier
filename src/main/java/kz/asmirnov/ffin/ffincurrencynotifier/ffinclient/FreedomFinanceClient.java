package kz.asmirnov.ffin.ffincurrencynotifier.ffinclient;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import kz.asmirnov.ffin.ffincurrencynotifier.ffinclient.dto.CurrencyListResponse;

@Client(id = "ffin")
public interface FreedomFinanceClient {

    @Get("/api/exchange-rates/getRates")
    CurrencyListResponse getActualRates();
}
