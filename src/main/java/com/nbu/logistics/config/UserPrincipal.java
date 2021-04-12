package com.nbu.logistics.config;

import java.util.Collection;

import com.nbu.logistics.entity.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;
	private User user;
	private Collection<GrantedAuthority> authorities;

	public UserPrincipal() {
	}

	public UserPrincipal(User user) {
		this.user = user;

		String[] userRoles = new String[0];
		this.authorities = AuthorityUtils.createAuthorityList(userRoles);
	}

	public long getUserId() {
		return this.user.getId();
	}

	public String getEmail() {
		return this.user.getEmail();
	}

	public void setEmail(String email) {
		this.user.setEmail(email);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
