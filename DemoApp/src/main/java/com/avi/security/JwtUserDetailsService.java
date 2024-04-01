package com.avi.security;

import com.avi.model.User;
import com.avi.model.dto.UserPrincipal;
import com.avi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findRecordByEmail(username).get(0);
		User user = userRepository.findById(Integer.parseInt(username)).orElseThrow(
				() -> new UsernameNotFoundException("User not found with username or email : " + username));
		return UserPrincipal.create(user);
	}

	public Boolean checkTokenLogout(String token){
		Boolean flag= userRepository.checkLogout(token);
		return flag;
	}
}