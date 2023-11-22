package kz.asmirnov.ffin.ffincurrencynotifier.repository;

import kz.asmirnov.ffin.ffincurrencynotifier.dto.Currency;
import kz.asmirnov.ffin.ffincurrencynotifier.entity.SubscriptionEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    private final JdbcTemplate jdbcTemplate;

    public SubscriptionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void deleteByChatId(Long chatId) {
        jdbcTemplate.update("delete from subscription where chat_id = ?", chatId);
    }

    @Override
    public SubscriptionEntity save(SubscriptionEntity subscriptionEntity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement("insert into subscription(chat_id, currency_buy, currency_sell) VALUES (?, ?, ?)", new String[]{"id"});
                    ps.setLong(1, subscriptionEntity.getChatId());
                    ps.setString(2, subscriptionEntity.getCurrencyBuy().toString());
                    ps.setString(3, subscriptionEntity.getCurrencySell().toString());
                    return ps;
                },
                keyHolder);
        subscriptionEntity.setId(keyHolder.getKeyAs(Long.class));
        return subscriptionEntity;
    }

    @Override
    public List<SubscriptionEntity> findAll() {
        return jdbcTemplate.query("select id, chat_id, currency_buy, currency_sell from subscription",
                (rs, rowNum) -> new SubscriptionEntity(
                        rs.getLong(1),
                        rs.getLong(2),
                        Currency.valueOf(rs.getString(3)),
                        Currency.valueOf(rs.getString(4))
                ));
    }
}
