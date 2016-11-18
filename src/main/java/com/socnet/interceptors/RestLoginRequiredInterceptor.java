package com.socnet.interceptors;

import com.socnet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class RestLoginRequiredInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("REST INTERCEPTOR: Is RestUser logged in: " + authService.isCurrentUserLoggedIn());
        if (authService.isCurrentUserLoggedIn()) {
            return true;
        }
        System.out.println("REST INTERCEPTOR: unauthorized rest request - sent HTTP-401 response");   //todo remove after testing
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}
