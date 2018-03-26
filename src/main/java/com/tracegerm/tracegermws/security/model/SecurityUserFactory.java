package com.tracegerm.tracegermws.security.model;


import com.tracegerm.tracegermws.model.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class SecurityUserFactory {

  public static TraceGermUser create(User user) {
    return new TraceGermUser(
      user.getId(),
      user.getUsername(),
      user.getPassword(),
      user.getEmail(),
      AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
    );
  }

}
