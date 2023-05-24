package com.example.nsn.rest;

import com.example.nsn.dto.UserDto;
import com.example.nsn.exception.UserAlreadyExistException;
import com.example.nsn.dto.AuthenticationRequest;
import com.example.nsn.dto.AuthenticationResponse;
import com.example.nsn.service.UserService;
import com.example.nsn.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:8081/")
public class RestControllerV1Authentication {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getEmail());

            final String jwt = jwtTokenUtil.generateToken(userDetails);

            return new AuthenticationResponse(jwt,userDetails.getUsername());
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);//TODO: return exception body
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        //TODO: check email
        if(userService.getByEmail(userDto.getEmail()) != null)
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());

        userService.addUser(userDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
