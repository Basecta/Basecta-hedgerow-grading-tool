package com.basecta.auth;

import com.basecta.auth.dto.LoginRequest;
import com.basecta.auth.dto.LoginResponse;
import com.basecta.auth.exception.AuthExceptionMessages;
import com.basecta.auth.exception.InvalidCredentialsException;
import com.basecta.auth.security.UserPrincipal;
import com.basecta.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {

        UsernamePasswordAuthenticationToken credentialsToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());


        // TODO: This is fine for now, but this try block should only contain the authenticate() call. Extract to its own method at a later date.
        try {
            Authentication auth = authenticationManager.authenticate((credentialsToken));
            UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
            User user = principal.user();

            String token = jwtService.generateToken(user.getId(), user.getEmail());

            return new LoginResponse(token, user.getId(), user.getUsername(), user.getEmail());
        }
        // TODO: Add exceptions here when enabling authentication features, e.g. email verification, account disabling, etc.
        catch (BadCredentialsException | UsernameNotFoundException ex) {
            throw new InvalidCredentialsException(AuthExceptionMessages.INVALID_CREDENTIALS);
        }
    }
}
