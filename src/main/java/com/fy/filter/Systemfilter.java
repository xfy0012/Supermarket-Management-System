package com.fy.filter;

import com.fy.pojo.User;
import com.fy.util.Constants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.logging.LogRecord;

/**
 * @author fanying
 * @version 1.0
 */
public class Systemfilter implements Filter {
    public  void init(FilterConfig filterConfig) throws ServletException {


    }
    public  void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);

        if (user == null) {
            response.sendRedirect("smbms/error.jsp");
        }else {
            chain.doFilter(req, resp);
        }

    }
    public void destroy() {

    }

}
