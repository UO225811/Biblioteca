package com.capgemini.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capgemini.model.Lector;
import com.capgemini.repository.LectorRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private LectorRepository lectorRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	      Lector lector= lectorRepository.findLectorByEmail(username);
	        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//	        grantedAuthorities.add(new SimpleGrantedAuthority("LECTOR"));
	        grantedAuthorities.add(new SimpleGrantedAuthority(lector.getRole()));
//	        if (lector == null) {
//	            throw new UsernameNotFoundException(username);
//	        }
	        grantedAuthorities.add(new SimpleGrantedAuthority(lector.getRole()));
	        return new org.springframework.security.core.userdetails.User(
	        		lector.getEmail(), lector.getPassword(), grantedAuthorities);
	}

}
