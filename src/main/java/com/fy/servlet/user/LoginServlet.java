package com.fy.servlet.user;

import com.fy.pojo.User;
import com.fy.service.user.UserService;
import com.fy.service.user.UserServiceImpl;
import com.fy.util.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author fanying
 * @version 1.0
 */
// controller to
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("==loginServlet--start==");

        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        System.out.println("userCode:" + userCode + "userPassword:" + userPassword);
        // to compare with the database in the service lay

        UserService userService = new UserServiceImpl();
        User user = userService.login(userCode, userPassword);
        if (user != null) {
            //put the user information into session.
            req.getSession().setAttribute(Constants.USER_SESSION, user);
            //go to the
            resp.sendRedirect("jsp/frame.jsp" );
        }else {
            req.setAttribute("error", "username is not correct");
            req.getRequestDispatcher("login.jsp").forward(req, resp);

        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
