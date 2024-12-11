package com.fy.service.user;

import com.fy.dao.BaseDao;
import com.fy.dao.User.UserDao;
import com.fy.dao.User.UserImpl;
import com.fy.pojo.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author fanying
 * @version 1.0
 */
public class UserServiceImpl implements UserService {
    //use dao lay,
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserImpl();
    }

    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;
        try {

            connection = BaseDao.getConnection();
            user = userDao.getLoginUser(connection, userCode);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return user;

    }

    @Override
    public boolean pwdModify(int id, String password) throws SQLException {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (userDao.pwsModify(connection, id, password) > 0) {
                flag = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    // query count
    @Override
    public int getUserCount(String userName, int userRole) {
        Connection connection = null;
        int count = 0;
        try {
            connection = BaseDao.getConnection();
            count = userDao.getUserCount(connection, userRole, userName);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return count;
    }


    @Test
    public void test() {
        UserServiceImpl userService = new UserServiceImpl();
        int UserCount = userService.getUserCount(null, 0);
        System.out.println(UserCount);
    }
}