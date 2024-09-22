package com.msa.springbaseproject.business.user;


import com.msa.springbaseproject.business.user.model.dto.UserDto;
import com.msa.springbaseproject.business.user.model.UserMapper;
import com.msa.springbaseproject.business.user.model.entity.User;
import com.msa.springbaseproject.business.user.model.request.AuthenticationRequest;
import com.msa.springbaseproject.business.user.model.request.RegisterRequest;
import com.msa.springbaseproject.business.user.model.response.AuthenticationResponse;
import com.msa.springbaseproject.common.BaseService;
import com.msa.springbaseproject.common.JWT.JwtService;
import com.msa.springbaseproject.common.JWT.Token;
import com.msa.springbaseproject.common.JWT.TokenRepository;
import com.msa.springbaseproject.common.Role;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService extends BaseService<User, UserRepository> {

    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository repository
                                , TokenRepository tokenRepository
                                , JwtService jwtService
                                , AuthenticationManager authenticationManager
                                , PasswordEncoder passwordEncoder) {
        super(repository);
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        User newUser = new User();
        newUser.setUsername(request.getUserName());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());
        newUser.setRole(Role.USER);
        User createdUser = repository.save(newUser);
        String jwtToken = jwtService.generateToken(createdUser);
        Token token = Token.builder()
                .userId(createdUser.getUserId())
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
        return AuthenticationResponse.builder()
                .userDto(UserMapper.mapToUserDto(createdUser))
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        Token token = Token.builder()
                .userId(user.getUserId())
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
        UserDto userDto = UserMapper.mapToUserDto(user);
        return AuthenticationResponse.builder()
                .userDto(userDto)
                .token(jwtToken)
                .build();
    }
}
