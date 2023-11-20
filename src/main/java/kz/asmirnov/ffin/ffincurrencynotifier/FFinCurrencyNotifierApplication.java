package kz.asmirnov.ffin.ffincurrencynotifier;

import kz.asmirnov.ffin.ffincurrencynotifier.dto.Currency;
import kz.asmirnov.ffin.ffincurrencynotifier.dto.CurrencyPair;
import kz.asmirnov.ffin.ffincurrencynotifier.service.RateUpdateService;
import kz.asmirnov.ffin.ffincurrencynotifier.service.SubscriptionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableScheduling
public class FFinCurrencyNotifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(FFinCurrencyNotifierApplication.class, args);
    }

    @RestController
    public static class TestController {

        private final SubscriptionService subscriptionService;
        private final RateUpdateService rateUpdateService;

        public TestController(SubscriptionService subscriptionService, RateUpdateService rateUpdateService) {
            this.subscriptionService = subscriptionService;
            this.rateUpdateService = rateUpdateService;
        }

        @GetMapping("/subscribe")
        public String subscribe() {
            rateUpdateService.saveRate(new CurrencyPair(Currency.EUR, Currency.RUB), BigDecimal.TEN);
            return "1";
        }
    }

}
