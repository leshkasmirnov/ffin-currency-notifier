package kz.asmirnov.ffin.ffincurrencynotifier.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "bot")
public record BotConfig(String name, String token) {
    @ConstructorBinding
    public BotConfig {
    }
}
