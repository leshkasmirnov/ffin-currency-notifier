package kz.asmirnov.ffin.ffincurrencynotifier.repository;

import kz.asmirnov.ffin.ffincurrencynotifier.dto.Currency;
import kz.asmirnov.ffin.ffincurrencynotifier.entity.RateUpdateEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class RateUpdateRepositoryImpl implements RateUpdateRepository {

    private final JdbcTemplate jdbcTemplate;

    public RateUpdateRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<RateUpdateEntity> findByCurrencyBuyAndCurrencySell(Currency currencyBuy, Currency currencySell) {
        return Optional.empty();
    }

    @Override
    public RateUpdateEntity save(RateUpdateEntity entity) {
        if (entity.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                        PreparedStatement ps =
                                connection.prepareStatement("insert into rate_update(currency_buy, currency_sell, last_rate, last_update) VALUES (?, ?, ?, ?)", new String[]{"id"});
                        ps.setString(1, entity.getCurrencyBuy().toString());
                        ps.setString(2, entity.getCurrencySell().toString());
                        ps.setBigDecimal(3, entity.getLastRate());
                        ps.setTimestamp(4, Timestamp.valueOf(entity.getLastUpdate()));
                        return ps;
                    },
                    keyHolder);
            entity.setId(keyHolder.getKeyAs(Long.class));
            return entity;
        } else {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps =
                        connection.prepareStatement("update rate_update set currency_buy = ?, currency_sell = ?, last_rate = ?, last_update = ? where id = ?");
                ps.setString(1, entity.getCurrencyBuy().toString());
                ps.setString(2, entity.getCurrencySell().toString());
                ps.setBigDecimal(3, entity.getLastRate());
                ps.setTimestamp(4, Timestamp.valueOf(entity.getLastUpdate()));
                ps.setLong(5, entity.getId());
                return ps;
            });
            return entity;
        }
    }

    @Override
    public Optional<RateUpdateEntity> findById(Long id) {
        List<RateUpdateEntity> result = jdbcTemplate.query("select id, currency_buy, currency_sell, last_rate, last_update from rate_update",
                (rs, rowNum) -> new RateUpdateEntity(
                        rs.getLong(1),
                        Currency.valueOf(rs.getString(2)),
                        Currency.valueOf(rs.getString(3)),
                        rs.getBigDecimal(4),
                        rs.getTimestamp(5).toLocalDateTime()

                ));
        return result.stream().findFirst();
    }
}
