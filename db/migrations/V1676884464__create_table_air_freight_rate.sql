CREATE TABLE air_freight_rates(
    id                                   UUID          NOT NULL      PRIMARY KEY   DEFAULT gen_random_uuid(),
    origin_airport_id                    UUID          NOT NULL,
    origin_country_id                    UUID          NOT NULL,
    origin_trade_id                      UUID          NOT NULL,
    origin_continent_id                  UUID          NOT NULL,
    destination_airport_id               UUID          NOT NULL,
    destination_country_id               UUID          NOT NULL,
    destination_trade_id                 UUID          NOT NULL,
    destination_continent_id             UUID          NOT NULL,
    commodity                            VARCHAR(50)   NOT NULL,
    commodity_type                       VARCHAR(50)   NOT NULL,
    commodity_sub_type                   VARCHAR(50)   NOT NULL,
    stacking_type                        VARCHAR(50)   NOT NULL,
    shipment_type                        VARCHAR(50)   NOT NULL,
    maximum_weight                       INTEGER       NOT NULL,
    length                               INTEGER       NOT NULL,
    breadth                              INTEGER       NOT NULL,
    height                               INTEGER       NOT NULL,
    last_rate_available_date            TIMESTAMP WITHOUT TIME ZONE NOT NULl,
    validity_id                          UUID          NOT NULL,
    airline_id                           UUID          NOT NULL,
    min_price                            FLOAT         NOT NULL,
    service_provider_id                  UUID          NOT NULL,
    weight_slabs                         JSONB         NOT NULL,
    is_best_price                        BOOLEAN       NOT NULL,
    origin_local_id                      UUID          NOT NULL,
    destination_local_id                 UUID          NOT NULL,
    surcharge_id                         UUID          NOT NULL,
    warehouse_rate_id                    UUID,
    rate_not_available_entry             BOOLEAN       NOT NULL,
    operation_type                       varchar(50)   NOT NULL,
    cogo_entity_id                       UUID          NOT NULL,
    price_type                           VARCHAR(50)   NOT NULL,
    created_at                           timestamp(6) without time zone NOT NULL,
    updated_at                           timestamp(6) without time zone NOT NULL,
    source                               VARCHAR(50)  DEFAULT NULL
    );