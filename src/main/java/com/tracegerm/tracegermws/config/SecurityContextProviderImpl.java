package com.tracegerm.tracegermws.config;


import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityContextProviderImpl implements SecurityContextProvider {

    @Override
    public SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }

    @Override
    public void setContext(SecurityContext securityContext) {
        SecurityContextHolder.setContext(securityContext);
    }

    @Override
    public UserDetails getUserDetails() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
