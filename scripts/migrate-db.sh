#!/bin/bash

set -e
docker pull flyway/flyway > /dev/null 2>&1
echo
echo "Starting migration of database with flyway"
DB_URL='jdbc:postgresql://login-epsilon1.dev.cogoport.io:6432/cogoport_api_staging?preparedStatementCacheQueries=0'
DB_PASS='E919A75364398A449F860AEADDDC57FA0502145A4E63959DDB33C417A48DC0DA'
DB_USER='staging'
docker run --rm -v $PWD/db/migrations:/flyway/sql flyway/flyway migrate -url=$DB_URL -user=$DB_USER -password=$DB_PASS
echo "Successfully migrated 'epsilon1' database"
