package com.basecta.auth;

import com.basecta.auth.security.UserPrincipal;
import com.basecta.user.User;
import com.basecta.user.UserRepository;
import com.basecta.user.exception.UserExceptionMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(UserExceptionMessages.USER_NOT_FOUND + email));

        return new UserPrincipal(user);
    }
}
