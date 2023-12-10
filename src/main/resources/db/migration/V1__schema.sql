create table if not exists subscription(
                                           id SERIAL PRIMARY KEY,
                                           chat_id BIGINT unique,
                                           currency_buy varchar(3) CHECK (currency_buy IN ('RUB', 'USD', 'EUR', 'KZT')),
    currency_sell varchar(3) CHECK (currency_sell IN ('RUB', 'USD', 'EUR', 'KZT'))
    );
create table if not exists rate_update(
                                          id SERIAL PRIMARY KEY,
                                          currency_buy varchar(3) CHECK (currency_buy IN ('RUB', 'USD', 'EUR', 'KZT')),
    currency_sell varchar(3) CHECK (currency_sell IN ('RUB', 'USD', 'EUR', 'KZT')),
    last_rate DECIMAL,
    last_update timestamp,
    UNIQUE (currency_buy, currency_sell)
    );

alter table subscription
alter column id type bigint using id::bigint;
alter table rate_update
alter column id type bigint using id::bigint;
