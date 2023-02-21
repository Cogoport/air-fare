CREATE TABLE air_freight_validity
(
 id  UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
 rate_id UUID NOT NULL,
 min_price FLOAT NOT NULL,
 currency VARCHAR(20) NOT NULL,
likes_count INTEGER NOT NULL,
dislikes_count INTEGER NOT NULL,
weight_slabs JSONB DEFAULT '[]',
density_category VARCHAR(20) DEFAULT 'general',
min_density_weight FLOAT,
max_density_weight FLOAT,
 status BOOL NOT NULL,
 validity_start TIMESTAMP WITHOUT TIME ZONE NOT NULL,
 validity_end TIMESTAMP WITHOUT TIME ZONE NOT NULL,
created_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);