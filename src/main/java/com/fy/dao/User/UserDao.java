package com.fy.dao.User;

import com.fy.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author fanying
 * @version 1.0
 */
public interface UserDao {

    public User getLoginUser(Connection connection, String userCode) throws SQLException;

    public int pwsModify(Connection connection, int id, String password) throws SQLException;

    // query customer total amount
    public int getUserCount(Connection connection, int UserRole, String UserName) throws SQLException;

    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize)throws Exception;
}
