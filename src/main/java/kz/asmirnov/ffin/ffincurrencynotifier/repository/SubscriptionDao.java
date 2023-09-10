package kz.asmirnov.ffin.ffincurrencynotifier.repository;

import kz.asmirnov.ffin.ffincurrencynotifier.data.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubscriptionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SubscriptionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Subscription> loadAll() {
        return jdbcTemplate.query("select * from subscription",
                (rs, i) -> new Subscription(rs.getLong("id"), rs.getLong("chat_id")));
    }
}
