package com.tracegerm.tracegermws.config;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityContextProvider {

    SecurityContext getContext();

    void setContext(SecurityContext securityContext);

    UserDetails getUserDetails();
}
