package asmirnov.bots.ffincurrencynotifier.bot;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@ConfigurationProperties("bot")
public record BotConfig(@NonNull @NotNull String name, @NonNull @NotNull @NotBlank String token) {
}
