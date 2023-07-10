package kz.asmirnov.ffin.ffincurrencynotifier.service;

import kz.asmirnov.ffin.ffincurrencynotifier.data.CurrencyListResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FFinService {

    private final RestTemplate restTemplate;
    private final String ffinUrl;

    public FFinService(RestTemplate restTemplate, @Value("${ffin-url}") String ffinUrl) {
        this.restTemplate = restTemplate;
        this.ffinUrl = ffinUrl;
    }

    public CurrencyListResponse getActualRates() {
        return restTemplate.getForObject(ffinUrl + "/api/exchange-rates/getRates", CurrencyListResponse.class);
    }
}
