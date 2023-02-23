CREATE TABLE air_freight_rate_audits(
    id                 UUID    NOT NULL  PRIMARY KEY   DEFAULT gen_random_uuid(),
    bulk_operation_id  UUID    DEFAULT NULL,
    procured_by_id     UUID      NOT NULL,
    sourced_by_id      UUID      NOT NULL,
    validity_id        UUID,
    rate_sheet_id      UUID    DEFAULT NULL,
    object_type        String   NOT NULL,
    object_id          UUID     NOT NULL,
    action_name        Sring    NOT NULL,
    performed_by_id    UUID     NOT NULL,
    data               JSONB    NOT NULL,
    created_at         timestamp(6) without time zone NOT NULL,
    updated_at         timestamp(6) without time zone NOT NULL
)