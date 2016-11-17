package com.socnet.interceptors;

import com.socnet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class LoginRequiredInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("INTERCEPTOR: Is user logged in: " + authService.isCurrentUserLoggedIn());
        if (authService.isCurrentUserLoggedIn()) {
            return true;
        }
        System.out.println("redirect unauthorised user");   //todo remove after testing
        response.sendRedirect("/socNet/login");             //todo remove "socNet"
        return false;
    }
}
