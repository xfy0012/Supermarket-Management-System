package com.fy.dao.User;

import com.fy.dao.BaseDao;
import com.fy.pojo.User;
import com.mysql.cj.util.StringUtils;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fanying
 * @version 1.0
 */
public class UserImpl implements UserDao{
    @Override
    public User getLoginUser(Connection connection, String userCode) throws SQLException {

        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;

        if(null != connection) {
            String sql = "SELECT * FROM smbms_user WHERE userCode=?";
            Object[] params = {userCode};
            rs = BaseDao.execute(connection, sql, params,rs, pstm );
            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
            }else{
                System.out.println("No user found with userCode: ");
            }

            BaseDao.closeResource(null,pstm,rs);


        }

        return user;
    }

    @Override
        public int pwsModify(Connection connection, int id, String password) throws SQLException {
            PreparedStatement pstm = null;
            int execute = 0;
            if (connection!=null){
                String sql = "update smbms_user set userPassword= ? where id = ?";
                Object params[] = {password,id};
                execute = BaseDao.execute(connection, pstm,sql, params);
                BaseDao.closeResource(null,pstm,null);
            }
            return execute;

    }

    @Override
    public int getUserCount(Connection connection, int userRole, String userName) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        if(null != connection) {
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole = r.id");
            List<Object> list = new ArrayList<Object>();

            if(!StringUtils.isNullOrEmpty(userName)) {
                sql.append(" and u.userName like ?");
                list.add('%' + userName + '%'); // index:0
            }
            if(     userRole > 0){
                sql.append("and u.userRole = ?");
                list.add(userRole); //index:1
            }
            
            Object[] params = list.toArray();
            rs = BaseDao.execute(connection,sql.toString(),params,rs,pst );
            if (rs.next()) {
                count= rs.getInt("count");
            }
            BaseDao.closeResource(null,pst,rs);
        }
        
        return count;
    }

    @Override
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        if(null != connection) {
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id");
            List<Object> list = new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(userName)) {
                sql.append(" and u.userName like ?");
                list.add('%' + userName + '%');
            }
            if(userRole > 0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }



            sql.append(" order by u.id desc limit ?,?");
            currentPageNo = (currentPageNo-1)*pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] params = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), params,rs, pstm);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setUserRole(rs.getInt("userRole"));
                userList.add(user);
            }

            BaseDao.closeResource(null,pstm,rs);

        }
        return userList;
    }

    @Test
    public void test() throws SQLException {
        UserDao userdao = new UserImpl();
        int count = 0;
        Connection connection = null;
        connection = BaseDao.getConnection();
        count = userdao.getUserCount(connection,0, null );
        System.out.println(count);
    }

    }





