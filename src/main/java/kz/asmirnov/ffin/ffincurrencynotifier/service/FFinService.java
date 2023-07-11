package kz.asmirnov.ffin.ffincurrencynotifier.service;

import kz.asmirnov.ffin.ffincurrencynotifier.data.CurrencyListResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FFinService {

    private final RestTemplate restTemplate;

    public FFinService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CurrencyListResponse getActualRates() {
        return restTemplate.getForObject("/api/exchange-rates/getRates", CurrencyListResponse.class);
    }
}
