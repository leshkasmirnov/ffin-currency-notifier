package kz.asmirnov.ffin.ffincurrencynotifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "kz.asmirnov.ffin.ffincurrencynotifier")
@ConfigurationPropertiesScan
@EnableScheduling
public class FFinCurrencyNotifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(FFinCurrencyNotifierApplication.class, args);
    }

    @RestController
    public static class TestController {
        private final RateJob rateJob;

        public TestController(RateJob rateJob) {
            this.rateJob = rateJob;
        }

        @GetMapping("/checkRate")
        public String subscribe() {
            rateJob.checkRate();
            return "1";
        }
    }

}
