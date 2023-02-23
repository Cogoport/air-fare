CREATE TABLE surcharges
(
    id               UUID          NOT NULL      PRIMARY KEY   DEFAULT gen_random_uuid(),
    code             VARCHAR(10)   NOT NULL,
    name             VARCHAR(50)   NOT NULL,
    units            VARCHAR[]     NOT NULL,
    condition        varchar       NOT NULL,
    tags             VARCHAR[]   NOT NULL,
    sac_code         VARCHAR(10)   NOT NULL
);