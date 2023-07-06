package br.com.tech.socialmedia3035.security.auth;

import br.com.tech.socialmedia3035.dtos.UserDTO;
import br.com.tech.socialmedia3035.repositories.UserRepository;
import br.com.tech.socialmedia3035.security.config.JwtService;
import br.com.tech.socialmedia3035.security.user.UserSecurity;
import br.com.tech.socialmedia3035.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final UserService service;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository, UserService service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register (RegisterRequest request){
        var userDTO = service.createUser(new UserDTO(request));
        UserSecurity userSecurity = new UserSecurity(userDTO);
        var jwtToken = jwtService.generateToken(userSecurity);
        return AuthenticationResponse.builder()
                .token(jwtToken).
                build();
    }

    public AuthenticationResponse authentication(AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = new UserDTO(repository.findByUsername(request.getUsername()).orElseThrow());
        UserSecurity userSecurity = new UserSecurity(user);
        var jwtToken = jwtService.generateToken(userSecurity);
        return AuthenticationResponse.builder()
                .token(jwtToken).
                build();
    }

}
