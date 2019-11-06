package business.helpers

import business.Contract
import groovy.util.logging.Log4j
import sun.reflect.generics.reflectiveObjects.NotImplementedException

@Log4j
class SQLDomainBuilder {

    // TODO - tests
    static String buildQueryToCreateDatabase(Contract contract) {
        // MSSQL
        // return "create database \"${contract.databaseName}\""

        // MYSQL
        return "CREATE DATABASE IF NOT EXISTS `${contract.databaseName}`";
    }

    // TODO - tests
    static String buildQueryToCheckIfDatabaseExists(Contract contract) {
        // MSSQL
        String query = "SELECT count(*) AS total FROM master.dbo.sysdatabases where name = '${contract.databaseName}'"
        log.info("Query to check if database exists: [$query]")
        return query
    }

    // TODO - tests
    static String resolveColumnType(Map field) {

        String type = field.type

        if (['text', 'cnpj', 'cpf', 'email'].contains(type)) {
            return "VARCHAR(255)"
        } else if (['numeric'].contains(type)) {
            return "LONG"
        } else if (['date', 'dateTime'].contains(type)) {
            return "DATETIME"
        } else if (['time'].contains(type)) {
            return "TIME"
        } else if (['textArea'].contains(type)) {
            return "TEXT"
        } else if (['currency'].contains(type)) {
            return "DECIMAL"
        }

        return NotImplementedException(type)
    }

    // TODO - essa query deve ser montada de acordo com o $contract.databaseDriver
    static String buildJdbcUrlConnection(Contract contract) {

        // MSSQL
        // query = "jdbc:jtds:sqlserver://$contract.databaseHost:$contract.databasePort;instanceName"

        // MYSQL
        return buildJdbcUrlConnection(contract.databaseHost, contract.databasePort, contract.databaseName, contract.databaseExtra)
    }

    static String buildJdbcUrlConnection(Contract contract, boolean openContractDatabase) {

        // MSSQL
        // query = "jdbc:jtds:sqlserver://$contract.databaseHost:$contract.databasePort;instanceName"

        // MYSQL
        return buildJdbcUrlConnection(
                contract.databaseHost,
                contract.databasePort,
                openContractDatabase ? contract.databaseName : null,
                contract.databaseExtra
        )
    }

    static String buildJdbcUrlConnection(String databaseHost, String databasePort, String databaseName, String databaseExtra) {

        String query

        // MSSQL
        // query = "jdbc:jtds:sqlserver://$contract.databaseHost:$contract.databasePort;instanceName"

        // MYSQL
        if (databaseName) {
            query = "jdbc:mysql://$databaseHost:$databasePort/$databaseName?useUnicode=yes&characterEncoding=UTF-8"
        } else {
            query = "jdbc:mysql://$databaseHost:$databasePort/?useUnicode=yes&characterEncoding=UTF-8"
        }
        log.info("JDBC String connection: [$query]")
        return query
    }
}
