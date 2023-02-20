CREATE TABLE air_freight_weight_slabs
(
    id  BIGSERIAL PRIMARY KEY,
    unit VARCHAR(20) ,
    lower_limit   FLOAT,
    upper_limit FLOAT,
    tariff_price FLOAT,
    currency VARCHAR(20),
    created_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);