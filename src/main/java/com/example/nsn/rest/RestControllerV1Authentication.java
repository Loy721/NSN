package com.example.nsn.rest;

import com.example.nsn.dto.UserDto;
import com.example.nsn.entity.User;
import com.example.nsn.entity.UserToken;
import com.example.nsn.execption.IncorrectUserOrPassword;
import com.example.nsn.execption.UserAlreadyExistException;
import com.example.nsn.dto.AuthenticationRequest;
import com.example.nsn.dto.AuthenticationResponse;
import com.example.nsn.mapper.UserMapper;
import com.example.nsn.service.UserService;
import com.example.nsn.service.UserTokenService;
import com.example.nsn.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("http://localhost:8081/")
public class RestControllerV1Authentication {
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtTokenUtil;

    private final UserDetailsService userDetailsService;

    private final UserService userService;

    private final UserTokenService userTokenService;

    @Autowired
    public RestControllerV1Authentication(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, UserDetailsService userDetailsService, UserService userService, UserTokenService userTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.userTokenService = userTokenService;
    }

    @PostMapping("/signin")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            final User user = userService.getByEmail(authenticationRequest.getEmail());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUid(), authenticationRequest.getPassword())
            );

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(user.getUid());

            final String jwt = jwtTokenUtil.generateToken(userDetails);

            userTokenService.save(new UserToken(user.getUid(), jwt));

            return new AuthenticationResponse(jwt, user.getUid(),
                    userDetails.getAuthorities().stream().map(Object::toString).collect(Collectors.toList()));
        } catch (Exception e) {
            throw new IncorrectUserOrPassword("Incorrect username or password", e);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        if (userService.isExistByEmail(userDto.getEmail()))
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());

        userService.addUser(UserMapper.userDtoToNewUser(userDto));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String jwt) {
        userTokenService.deleteByToken(jwt);
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
