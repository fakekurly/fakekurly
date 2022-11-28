package com.board;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.board.security.service.MemberUserDetailsService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration

public class WebSecurityConfig {

	private final AuthSucessHandler authSucessHandler;
    private final AuthFailureHandler authFailureHandler;
	private final MemberUserDetailsService memberUserDetailsService;
	
	// 비밀번호 암호화
	@Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() { 
		log.info("Static Resource에 대한 접근 권한 설정 완료 !!!");
	    return (web)-> web.ignoring().antMatchers("/images/**","/profile/**");
		    
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
	    http.authorizeRequests() 	    	
	    	.antMatchers("/member/**").permitAll()
	    	.antMatchers("/product/**").permitAll()	//로그인하지 않아도 쇼핑몰을 구경할 수 있도록
	    	.antMatchers("/userManage/**").hasAnyAuthority("USER","ADMIN")
	    	.antMatchers("/board/**").hasAnyAuthority("USER","ADMIN")
	    	.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
	        .anyRequest().authenticated();
    

	    http
	    	.formLogin()
	    	.usernameParameter("userid")
	        .loginPage("/member/login") // 로그인 페이지 링크
	    	.successHandler(authSucessHandler) // 성공시 요청을 처리할 핸들러
	        .failureHandler(authFailureHandler); // 실패시 요청을 처리할 핸들러

	    http.csrf().disable();
	    http.cors().disable();
	    
	    //로그아웃 처리
	    http    
	    	.logout()
	    	.logoutUrl("/userManage/logout")
	    	//.logoutRequestMatcher(new AntPathRequestMatcher("/userManage/logout")) // 로그아웃 URL
	    	.logoutSuccessUrl("/member/login") // 로그아웃 성공시 리다이렉트 주소
			.invalidateHttpSession(true) // 세션 삭제
	    	.deleteCookies("JSESSIONID","remember-me") // JSESSIONID, remember-me 쿠키 삭제
	    	.permitAll();
	    
	    //세션 처리
	    http
	    	.sessionManagement()
	    	.maximumSessions(1) // 세션 최대 허용 수 1, -1인 경우 무제한 세션 허용
	    	.maxSessionsPreventsLogin(false) // true면 중복 로그인을 막고, false면 이전 로그인의 세션을 해제
	    	.expiredUrl("/member/login?exception=SESSION_OUT"); // 세션이 만료된 경우 이동 할 페이지를 지정
        
        http
        	.rememberMe() // 로그인 유지
        	.key("xavier")
        	.alwaysRemember(false) // 항상 기억할 것인지 여부
        	.tokenValiditySeconds(43200) // in seconds, 12시간 유지
        	.rememberMeParameter("remember-me")
	    	.userDetailsService(memberUserDetailsService)
	    	.authenticationSuccessHandler(authSucessHandler);
	    
	    log.info("Application 접근 권한 설정 완료 !!!");
	    return http.build();
	}

}
