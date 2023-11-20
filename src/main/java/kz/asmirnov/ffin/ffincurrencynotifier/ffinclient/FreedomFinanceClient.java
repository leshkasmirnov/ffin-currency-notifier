package kz.asmirnov.ffin.ffincurrencynotifier.ffinclient;

import kz.asmirnov.ffin.ffincurrencynotifier.ffinclient.dto.CurrencyListResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FreedomFinanceClient {

    private final RestTemplate restTemplate;

    public FreedomFinanceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CurrencyListResponse getActualRates() {
        return restTemplate.getForObject("/api/exchange-rates/getRates", CurrencyListResponse.class);
    }
}
