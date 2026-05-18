package com.basecta.user;

import com.basecta.user.dto.RegisterUserRequest;
import com.basecta.user.dto.UserResponse;
import com.basecta.user.exception.EmailAlreadyTakenException;
import com.basecta.user.exception.UploadMessages;
import com.basecta.user.exception.UsernameAlreadyTakenException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public UserResponse register(RegisterUserRequest request) {

        if (userRepository.existsByUsername(request.username())) {
            throw new UsernameAlreadyTakenException(UploadMessages.USERNAME_TAKEN);
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyTakenException(UploadMessages.EMAIL_TAKEN);
        }

        String passwordHash = passwordEncoder.encode(request.password());

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .passwordHash(passwordHash)
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.from(savedUser);

    }
}
