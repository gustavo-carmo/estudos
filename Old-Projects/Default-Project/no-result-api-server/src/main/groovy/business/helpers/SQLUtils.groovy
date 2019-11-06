package business.helpers

import business.Contract
import groovy.sql.Sql
import groovy.util.logging.Log4j

@Log4j
class SQLUtils {

    // TODO - tests - contract é requerido
    static Sql buildSQLConnection(Contract contract, boolean openContractDatabase = true) {
        return buildSQLConnection(
                SQLDomainBuilder.buildJdbcUrlConnection(contract, openContractDatabase),
                contract.databaseUser,
                contract.databasePass,
                contract.databaseDriver.jdbcDriver
        )
    }

    // TODO - tests - todos campos são requeridos
    static Sql buildSQLConnection(
            String databaseUrl,
            String databaseUser,
            String databasePass,
            String jdbcDriver) {

        log.info("Open connection databaseUrl [$databaseUrl], databaseUser [$databaseUser], databasePass [*****], jdbcDriver [$jdbcDriver]")

        return Sql.newInstance(databaseUrl,
                databaseUser,
                databasePass,
                jdbcDriver
        )
    }

    static def execute(Sql sqlConnection, String query) {
        log.info("Execute query: [$query]")
        sqlConnection.execute(query)
    }

    static def close(Sql sqlConnection) {
        log.info("Close connection")
        sqlConnection.close()
    }

    // TODO - tests
    static List executeSelect(Sql sqlConnection, String query) {
        log.info("Execute executeSelect: [$query]")
        return sqlConnection.rows(query)
    }
}
