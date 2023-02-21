CREATE TABLE local_rates
(
    id                                   UUID          NOT NULL      PRIMARY KEY   DEFAULT gen_random_uuid(),
    airline_id                           UUID          NOT NULL,
    airport_id                           UUID          NOT NULL,
    country_id                           UUID          NOT NULL,
    trade_id                             UUID          NOT NULL,
    continent_id                         UUID          NOT NULL,
    trade_type                           VARCHAR(10)   NOT NULL,
    commodity                            VARCHAR(50)   NOT NULL,
    commodity_type                       VARCHAR(50)   NOT NULL,
    service_provider_id                  UUID          NOT NULL,
    line_items                           JSONB         NOT NULL,
    is_line_items_error_messages_present BOOLEAN,
    is_line_items_info_messages_present  BOOLEAN,
    line_items_error_messages            JSONB,
    line_items_info_messages             JSONB,
    source                               VARCHAR(50)  DEFAULT NULL,
    created_at                           timestamp(6) without time zone NOT NULL,
    updated_at                           timestamp(6) without time zone NOT NULL
);