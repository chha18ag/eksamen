package server.repository;

import server.util.Config;

import java.sql.*;

/**
 * Contains basic methods for repositories. Should not require modification.
 * Uses Hikari connection pools.
 */
class BaseRepository {

    BaseRepository() {

    }

    private Connection getConnection() throws SQLException {
        return Config.getHikariDataSource().getConnection();
    }

    /**
     * Creates a prepared statement for executing against the database with parameters.
     * @param sql The SQL to be executed.
     * @return The prepared statement.
     */
    PreparedStatement prepareQuery(String sql) {

        ResultSet rs = null;
        try {
            return getConnection().prepareStatement(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Executes a prepared statement against the database.
     * @param ps The prepared statement.
     * @return The resultset.
     */
    ResultSet executePreparedStatementQuery(PreparedStatement ps) {
        ResultSet rs = null;
        try {
            if(ps.execute())
                rs = ps.getResultSet();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    /**
     * Makes a prepared statement from SQL, which allows to set parameters, like:
     * ps.setString(1, [STRING VALUE]);
     *
     * @param sql An SQL string, with parameters.
     * @return The prepared statement, which can be executed with executeInsertPreparedStatement
     */
    public PreparedStatement prepareInsert(String sql) {
        try {
            return getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Executes a previously prepared inser statement against the database.
     * @param statement The prepared statement
     * @return The value of primary key generated by executing the prepared statement
     */
    public int executeInsertPreparedStatement(PreparedStatement statement) {
        int result = 0;

        try {
            result = statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
