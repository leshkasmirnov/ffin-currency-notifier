package kz.asmirnov.ffin.ffincurrencynotifier;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.runtime.Micronaut;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

public class FFinCurrencyNotifierApplication {

    public static void main(String[] args) {
        Micronaut.run(FFinCurrencyNotifierApplication.class);
    }

    @Controller
    @ExecuteOn(TaskExecutors.BLOCKING)
    public static class JobController {
        private final RateJob rateJob;

        public JobController(RateJob rateJob) {
            this.rateJob = rateJob;
        }

        @Get("/checkRate")
        public String checkRate() {
            rateJob.checkRate();
            return "ok";
        }
    }

}
