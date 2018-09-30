package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.AccountDAO;
import by.htp.hvozdzeu.dao.InstanceDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.AccountRowMapper;
import by.htp.hvozdzeu.model.Account;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class AuthDAOImpl extends AccountRowMapper implements InstanceDAO, AccountDAO {

    private static final String NOT_CREATE_TOKEN = "not found account";

    private static final String CHECK_ACCOUNT = "SELECT * FROM `bankservice`.`account` WHERE `Login` = ? AND `Password` = ?;";
    private static final String SAVE_TOKEN = "INSERT INTO `bankservice`.`tokens` (`Token`, `Date`, `Time`, `Available`) VALUES (?, ?, ?, ?);";
    private static final String FIND_TOKEN = "SELECT * FROM `tokens` WHERE `tokens`.`Token` = ?;";

    private static final String ERROR_SQL_CHECK_ACCOUNT = "Error check user.";
    private static final String ERROR_SQL_SAVE_TOKEN = "Error save token.";
    private static final String ERROR_FIND_TOKEN = "Error find token.";

    @Override
    public String getToken(Account account) throws DAOException {
        String token;
        String login = account.getLogin();
        String password = account.getPassword();
        Account checkAccount = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ACCOUNT)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    checkAccount = buildAccountRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_SQL_CHECK_ACCOUNT);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }   
        
        if(checkAccount != null){
            token = UUID.randomUUID().toString();
            saveToken(token);
        } else {
            token = NOT_CREATE_TOKEN;
        }

        return token;
    }

    @Override
    public boolean findToken(String restToken) throws DAOException {
        boolean result;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TOKEN)) {
            preparedStatement.setString(1, restToken);
            preparedStatement.executeQuery();
            result = true;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return result;
    }

    private void saveToken(String tokenRest) throws DAOException {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_TOKEN)) {
            preparedStatement.setString(1, tokenRest);
            preparedStatement.setDate(2, Date.valueOf(date));
            preparedStatement.setTime(3, Time.valueOf(time));
            preparedStatement.setBoolean(4, true);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(ERROR_SQL_SAVE_TOKEN);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
    }


}
