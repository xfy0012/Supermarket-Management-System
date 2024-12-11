package com.fy.service.user;

import com.fy.pojo.User;

import java.sql.SQLException;

/**
 * @author fanying
 * @version 1.0
 */
public interface UserService {
    //user login
    public User login(String userCode, String password);

    public boolean pwdModify(int id, String password) throws SQLException;;

    public int getUserCount(String userName, int userRole);

}
