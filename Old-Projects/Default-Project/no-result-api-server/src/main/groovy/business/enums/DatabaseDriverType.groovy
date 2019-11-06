package business.enums

enum DatabaseDriverType {

    MSSQL('net.sourceforge.jtds.jdbc.Driver'),
    MYSQL('com.mysql.jdbc.Driver')

    String jdbcDriver

    def DatabaseDriverType(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver
    }
}