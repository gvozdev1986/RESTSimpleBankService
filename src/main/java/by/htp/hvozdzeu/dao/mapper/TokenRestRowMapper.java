package by.htp.hvozdzeu.dao.mapper;

import by.htp.hvozdzeu.model.Token;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for build entity from ResultSet query use Builder pattern
 */
public class TokenRestRowMapper {

    private static final String TOKEN_ID = "Id";
    private static final String TOKEN = "Token";
    private static final String TOKEN_DATE = "Date";
    private static final String TOKEN_TIME = "Time";
    private static final String TOKEN_AVAILABLE = "Available";

    protected Token buildTokenRowMapper(ResultSet resultSet) throws SQLException {
        return Token.getBuilder()
                .id(resultSet.getLong(TOKEN_ID))
                .tokenRest(resultSet.getString(TOKEN))
                .date(resultSet.getDate(TOKEN_DATE).toLocalDate())
                .time(resultSet.getTime(TOKEN_TIME).toLocalTime())
                .available(resultSet.getBoolean(TOKEN_AVAILABLE))
                .build();
    }


}
