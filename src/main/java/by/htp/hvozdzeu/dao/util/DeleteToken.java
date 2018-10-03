package by.htp.hvozdzeu.dao.util;

import by.htp.hvozdzeu.dao.connection.ConnectionPool;
import by.htp.hvozdzeu.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The method for delete token after each transaction
 */
public class DeleteToken {

    private static final String SQL_DELETE_TOKEN = "DELETE FROM tokens WHERE tokens.Token = ?;";
    private static final String ERROR_SQL_DELETE_TOKEN = "Error delete token.";

    /**
     * Instance ConnectionPool for connect with DB in DAO implements
     */
    private static ConnectionPool dataBaseConnection = ConnectionPool.getInstance();

    /**
     * Private constructor
     */
    private DeleteToken() {
    }

    /**
     * The method for deleting token
     *
     * @param tokenRest String token
     * @throws DAOException exception
     */
    public static void deleteToken(String tokenRest) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_TOKEN)) {
            preparedStatement.setString(1, tokenRest);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(ERROR_SQL_DELETE_TOKEN);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
    }

}
