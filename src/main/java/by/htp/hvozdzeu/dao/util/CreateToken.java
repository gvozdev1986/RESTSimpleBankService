package by.htp.hvozdzeu.dao.util;

import by.htp.hvozdzeu.dao.InstanceDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The class for save new tokenRest to DB
 */
public class CreateToken implements InstanceDAO {

    private static final String SAVE_TOKEN = "INSERT INTO `bankservice`.`tokens` (`Token`, `Date`, `Time`) VALUES (?, ?, ?);";
    private static final String ERROR_SQL_SAVE_TOKEN = "Error save token.";

    private CreateToken() {
    }

    /**
     * The method for save tokenRest to DB
     *
     * @param tokenRest String created tokenRest
     * @throws DAOException Exception
     */
    public static void saveToken(String tokenRest) throws DAOException {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_TOKEN)) {
            preparedStatement.setString(1, tokenRest);
            preparedStatement.setDate(2, Date.valueOf(date));
            preparedStatement.setTime(3, Time.valueOf(time));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(ERROR_SQL_SAVE_TOKEN);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
    }


}
