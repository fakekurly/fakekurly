package com.kurly.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kurly.dto.MemberVO;
import com.kurly.mapper.MemberMapper;
import com.kurly.security.dto.MemberAuthDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Service
@Log4j2
public class MemberUserDetailsService implements UserDetailsService {

  private final MemberMapper mapper;
    
  @Override 
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 

	  log.info("MemberUserDetailsService loadUserByUsername = " + username);
	  
	  // 사용자의 정보를 가져와서, 일치하지 않으면 거부하는 듯?
	  Optional<MemberVO> memberAuth = Optional.ofNullable(mapper.memberInfoView(username));
	  if(!memberAuth.isPresent()) {
		  throw new UsernameNotFoundException("아이디/패스워드가 부정확합니다.");
	  }
	  // 정상적으로 사용자의 정보가 들어왔을 경우+
	  MemberVO member = memberAuth.get();
	  
	  /* // 위 구문과 동일한 내용
	  MemberVO member = mapper.memberInfoView(username);
	  if(memberAuth == null) throw new UsernameNotFoundException("아이디/패스워드가 부정확합니다.");
	  */
	  
	  // 데이터타입을 지정해주어야 하지만 List<dataType> 없이 써도 작동은 한대 (경고 노란줄)
	  // Role 값을 List 타입으로 구성된 grantAuthorities에 추가한다...
	  // grant 부여하다. 권한을 부여하는 구문인 듯.
	  List grantedAuthorities = new ArrayList<>();
	  SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getRole());
      grantedAuthorities.add(grantedAuthority);
      
      // username: 아이디.
      /* loadUserByUsername 메소드의 최종 목표: User 객체에 ID,PW,Role을 넣어주는 것 */
      User user = new User(username, member.getPassword(), grantedAuthorities);
      
      log.info("role = " + member.getRole());

	  return user;

  }

}
	

