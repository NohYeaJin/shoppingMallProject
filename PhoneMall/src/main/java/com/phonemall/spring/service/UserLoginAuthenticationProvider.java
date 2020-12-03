package com.phonemall.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	UserDetailsService service;
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String loginId = authentication.getName();
		String loginUserPw = (String) authentication.getCredentials();
		
		UserDetails userdetail = service.loadUserByUsername(loginId);
		
		if(userdetail == null || !loginId.equals(userdetail.getUsername())
				|| !pwEncoder.matches(loginUserPw, userdetail.getPassword())) {
			throw new BadCredentialsException(loginId);
		// 계정 정보 맞으면 나머지 부가 메소드 체크 (이부분도 필요한 부분만 커스터마이징 하면 됨)
		// 잠긴 계정일 경우
		}else if (!userdetail.isAccountNonLocked()) {
			throw new LockedException(loginId);

		// 비활성화된 계정일 경우
		}else if (!userdetail.isEnabled()) {
			throw new DisabledException(loginId);

		// 만료된 계정일 경우
		} else if (!userdetail.isAccountNonExpired()) {
			throw new AccountExpiredException(loginId);

		// 비밀번호가 만료된 경우
		} else if (!userdetail.isCredentialsNonExpired()) {
			throw new CredentialsExpiredException(loginId);
		}
		
		/* 최종 리턴 시킬 새로만든 Authentication 객체 */
		Authentication newAuth = new UsernamePasswordAuthenticationToken(
				userdetail, null, userdetail.getAuthorities());
		
		return newAuth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
