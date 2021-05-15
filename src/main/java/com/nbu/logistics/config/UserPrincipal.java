package com.nbu.logistics.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.nbu.logistics.entity.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;
	private User user;
	private Collection<GrantedAuthority> authorities;

	public UserPrincipal() {
	}

	public UserPrincipal(User user) {
		this.user = user;

		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		this.authorities = authorities;
	}

	public UserPrincipal(User user, Collection<GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
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

	public List<String> getRolesAsString() {
		return user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toList());
	}

}
