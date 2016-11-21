package com.socnet.interceptors;

import com.socnet.service.AuthService;
import com.socnet.service.MessageService;
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

    @Autowired
    private MessageService messageService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("INTERCEPTOR: Is user logged in: " + authService.isCurrentUserLoggedIn());
        if (authService.isCurrentUserLoggedIn()) {

/// TODO: 21.11.2016 remove tests
            messageService.addMessage("a", "b", String.valueOf(Math.random()));

            System.out.println(messageService.getLastMessages("a", "b", 50));
            System.out.println(messageService.getLastMessages("a", "b", 2));

            return true;
        }
        System.out.println("INTERCEPTOR: redirect unauthorized user");   //todo remove after testing
        response.sendRedirect("/login");
        return false;
    }
}
