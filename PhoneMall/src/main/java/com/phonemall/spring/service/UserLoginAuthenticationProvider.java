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
		// ���� ���� ������ ������ �ΰ� �޼ҵ� üũ (�̺κе� �ʿ��� �κи� Ŀ���͸���¡ �ϸ� ��)
		// ��� ������ ���
		}else if (!userdetail.isAccountNonLocked()) {
			throw new LockedException(loginId);

		// ��Ȱ��ȭ�� ������ ���
		}else if (!userdetail.isEnabled()) {
			throw new DisabledException(loginId);

		// ����� ������ ���
		} else if (!userdetail.isAccountNonExpired()) {
			throw new AccountExpiredException(loginId);

		// ��й�ȣ�� ����� ���
		} else if (!userdetail.isCredentialsNonExpired()) {
			throw new CredentialsExpiredException(loginId);
		}
		
		/* ���� ���� ��ų ���θ��� Authentication ��ü */
		Authentication newAuth = new UsernamePasswordAuthenticationToken(
				userdetail, null, userdetail.getAuthorities());
		
		return newAuth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
