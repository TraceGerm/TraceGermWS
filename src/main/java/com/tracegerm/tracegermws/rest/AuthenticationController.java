package com.tracegerm.tracegermws.rest;

import com.tracegerm.tracegermws.config.SecurityContextProvider;
import com.tracegerm.tracegermws.model.User;
import com.tracegerm.tracegermws.repository.UserRepository;
import com.tracegerm.tracegermws.security.TokenUtils;
import com.tracegerm.tracegermws.security.model.AuthenticationRequest;
import com.tracegerm.tracegermws.security.model.AuthenticationResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("${tracegerm.route.authentication}")
public class AuthenticationController {

  private final Logger logger = Logger.getLogger(this.getClass());

  @Value("${tracegerm.token.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenUtils tokenUtils;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private UserRepository userRepository;

  private final SecurityContextProvider securityContextProvider;

  @Autowired
  public AuthenticationController(SecurityContextProvider securityContextProvider) {
    this.securityContextProvider = securityContextProvider;
  }

  @GetMapping
  @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
  public ResponseEntity<?> getUserByToken() throws AuthenticationException {

    User user = this.userRepository.findByUsername(securityContextProvider.getUserDetails().getUsername());

    return ResponseEntity.ok(user);
  }

  @PostMapping
  public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {

    // Perform the authentication
    Authentication authentication = this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Reload password post-authentication so we can generate token
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    String token = this.tokenUtils.generateToken(userDetails, device);

    // Return the token
    return ResponseEntity.ok(new AuthenticationResponse(token));
  }

    @PostMapping("/admin")
    public ResponseEntity<?> authenticationAdminRequest(@RequestBody AuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {

        // Perform the authentication
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-authentication so we can generate token
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        for (GrantedAuthority authority : roles) {
            if(authority.getAuthority().contains("ROLE_ADMIN")) {
                String token = this.tokenUtils.generateToken(userDetails, device);
                return ResponseEntity.ok(new AuthenticationResponse(token));
            }
        }
        return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
}
