package com.fy.servlet.user;

import com.fy.pojo.User;
import com.fy.service.user.UserService;
import com.fy.service.user.UserServiceImpl;
import com.fy.util.Constants;
import com.mysql.cj.util.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author fanying
 * @version 1.0
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String newPassword = req.getParameter("newpassword");
        System.out.println("servlet" + newPassword);


        boolean flag = false;
        if (o != null && !StringUtils.isNullOrEmpty(newPassword)) {
            UserService userService = new UserServiceImpl();
            try {
                flag = userService.pwdModify(((User)o).getId(), newPassword);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(flag){
                req.setAttribute("message", "change password success");
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else {
                req.setAttribute("message", "change password failed");
            }
        }else {
            req.setAttribute("message", "please login");
        }

        req.getRequestDispatcher("pwdmodify.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    }

}
