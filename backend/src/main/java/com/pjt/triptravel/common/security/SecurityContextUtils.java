package com.pjt.triptravel.common.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.pjt.triptravel.common.jwt.TokenConst;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityContextUtils {

	private final UserDetailsServiceImpl userDetailsService;

	public void setAuthentication(String accessToken) {
		Authentication authentication = getAuthentication(accessToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private Authentication getAuthentication(String token) {
		User user = userDetailsService.loadUserByUsername(this.getUsername(token));
		return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
	}

	private String getUsername(String accessToken) {
		return Jwts.parser().setSigningKey(TokenConst.ACCESS_SECRET_KEY).parseClaimsJws(accessToken).getBody().getSubject();
	}
}
