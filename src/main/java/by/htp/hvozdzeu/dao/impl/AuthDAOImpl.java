package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.AccountDAO;
import by.htp.hvozdzeu.dao.InstanceDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.AccountRowMapper;
import by.htp.hvozdzeu.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static by.htp.hvozdzeu.dao.util.CreateToken.saveToken;

public class AuthDAOImpl extends AccountRowMapper implements InstanceDAO, AccountDAO {

    private static final String CHECK_ACCOUNT = "SELECT * FROM `bankservice`.`account` WHERE `Login` = ? AND `Password` = ?;";
    private static final String FIND_TOKEN = "SELECT * FROM `tokens` WHERE `tokens`.`Token` = ?;";

    private static final String ERROR_SQL_CHECK_ACCOUNT = "Error check user.";
    private static final String ERROR_FIND_TOKEN = "Error find token.";

    private static final String NOT_CREATE_TOKEN = "not found account";

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
            throw new DAOException(ERROR_FIND_TOKEN);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return result;
    }

}
