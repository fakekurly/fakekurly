package com.kurly;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.kurly.dto.MemberVO;
import com.kurly.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Component
public class AuthSucessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final MemberService service;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //service.logindateUpdate(authentication.getName());
        
        MemberVO member = service.login(authentication.getName());
        
        HttpSession session = request.getSession();              
		session.setAttribute("userid",member.getUserid());
		session.setAttribute("username",member.getUsername());
		session.setAttribute("role", member.getRole());
		log.info("Session 설정 완료 !!!");

		
		/*//패스워드 변경 후 30일이 경과했는지 확인
		MemberVO pwcheck = new MemberVO();
		pwcheck = service.pwcheck(member.getUserid());
		
		if(pwcheck.getPwdiff() > (30*pwcheck.getPwcheck())) {
			setDefaultTargetUrl("/userManage/pwCheckNotice"); 
		}else setDefaultTargetUrl("/userManage/welcome");
		*/
		
		setDefaultTargetUrl("/main");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}