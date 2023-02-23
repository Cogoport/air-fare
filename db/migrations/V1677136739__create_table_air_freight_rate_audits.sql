CREATE TABLE air_freight_rate_audits(
    id                 UUID    NOT NULL  PRIMARY KEY   DEFAULT gen_random_uuid(),
    bulk_operation_id  UUID    DEFAULT NULL,
    procured_by_id     UUID    DEFAULT NULL,
    sourced_by_id      UUID    DEFAULT NULL,
    validity_id        UUID    DEFAULT NULL,
    rate_sheet_id      UUID    DEFAULT NULL,
    object_type        varchar(50)   NOT NULL,
    object_id          UUID     NOT NULL,
    action_name        varchar(50)    NOT NULL,
    performed_by_id    UUID     NOT NULL,
    data               JSON    NOT NULL,
    created_at         timestamp(6) without time zone NOT NULL,
    updated_at         timestamp(6) without time zone NOT NULL
)