#!/bin/bash

$GLASSFISH_HOME/bin/asadmin start-domain


$GLASSFISH_HOME/bin/asadmin create-jdbc-connection-pool \
  --datasourceclassname org.postgresql.ds.PGSimpleDataSource \
  --restype javax.sql.DataSource \
  --property portNumber=5432:password=020904:user=postgres:databaseName=blog \
  jdbc/PostgresDataSource

$GLASSFISH_HOME/bin/asadmin create-jdbc-resource --connectionpoolid jdbc/PostgresDataSource jdbc/PostgresPool

$GLASSFISH_HOME/bin/asadmin stop-domain
