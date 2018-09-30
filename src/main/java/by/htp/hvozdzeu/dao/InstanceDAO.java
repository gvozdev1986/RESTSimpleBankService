package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.connection.ConnectionPool;
import by.htp.hvozdzeu.dao.util.RebasePassword;
import by.htp.hvozdzeu.util.PasswordEncoder;

/**
 * The interface for instance classes
 */
public interface InstanceDAO {

    /**
     * Instance ConnectionPool for connect with DB in DAO implements
     */
    ConnectionPool dataBaseConnection = ConnectionPool.getInstance();

    /**
     * Instance RebasePassword for get password from char array
     */
    RebasePassword rebasePassword = RebasePassword.getInstance();

    /**
     * Instance PasswordEncoder for get encoded password in md5 format
     */
    PasswordEncoder passwordEncoder = PasswordEncoder.getInstance();

}
