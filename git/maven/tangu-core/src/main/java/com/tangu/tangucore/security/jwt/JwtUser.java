package com.tangu.tangucore.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * @author fenglei on 8/29/17.
 */
@SuppressWarnings("ALL")
public class JwtUser implements UserDetails {
    //便于后面的controller里hasRole的使用
    public static final String GRENT_PREFEX = "ROLE_";

    private final Long id;
    private final String username;
    private final String password;
    private final String tenant;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    private final Date lastPasswordResetDate;



    public JwtUser(
            Long id,
            String tenant,
            String username,
            String password,
            String role,
            boolean enabled,
            Date lastPasswordResetDate
    ) {
        this.id = id;
        this.tenant = tenant;
        this.username = username;
        this.password = password;
        this.authorities = Arrays.asList(new SimpleGrantedAuthority(GRENT_PREFEX+role));
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public JwtUser(
            Long id,
            String tenant,
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            Date lastPasswordResetDate
    ) {
        this.id = id;
        this.tenant = tenant;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    public String getTenant() {
        return tenant;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}
