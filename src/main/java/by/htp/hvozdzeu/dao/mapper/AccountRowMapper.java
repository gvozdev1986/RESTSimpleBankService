package by.htp.hvozdzeu.dao.mapper;

import by.htp.hvozdzeu.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper {

    private static final String USER_ID = "Id";
    private static final String USER_LOGIN = "Login";
    private static final String USER_PSWD = "Password";

    protected Account buildAccountRowMapper(ResultSet resultSet) throws SQLException {
        return Account.getBuilder()
                .id(resultSet.getLong(USER_ID))
                .login(resultSet.getString(USER_LOGIN))
                .password(resultSet.getString(USER_PSWD))
                .build();
    }

}
