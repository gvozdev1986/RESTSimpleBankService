package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.connection.ConnectionPool;
import by.htp.hvozdzeu.dao.util.RebasePassword;
import by.htp.hvozdzeu.util.PasswordEncoder;

public interface InstanceDAO {

    ConnectionPool dataBaseConnection = ConnectionPool.getInstance();
    RebasePassword rebasePassword = RebasePassword.getInstance();
    PasswordEncoder passwordEncoder = PasswordEncoder.getInstance();

}
