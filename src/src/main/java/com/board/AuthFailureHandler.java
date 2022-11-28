package com.board;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, 
    		AuthenticationException exception) throws IOException, ServletException {

        String msg = "";

        // exception 관련 메세지 처리
        if (exception instanceof UsernameNotFoundException) {
            msg = "ID_NOT_FOUND";
        } else if (exception instanceof BadCredentialsException) {
            msg = "AUTHORITY_NOT_PERMITTED";
        }

        // GET 방식으로 메시지를 전달한다.
        setDefaultFailureUrl("/member/login?message=" + msg);
        super.onAuthenticationFailure(request, response, exception);

    }
}