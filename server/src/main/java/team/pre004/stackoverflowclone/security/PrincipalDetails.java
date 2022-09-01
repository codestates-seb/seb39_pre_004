package team.pre004.stackoverflowclone.security;

import lombok.*;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.pre004.stackoverflowclone.domain.user.entity.Users;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails{

    private static final long serialVersionUID = 1L;
    private Users owner;


    private Map<String, Object> attributes;
    @Builder
    public PrincipalDetails(Users owner) {
        this.owner = owner;
    }

    public PrincipalDetails(Users owner, Map<String, Object> attributes) {
        this.owner = owner;
        this.attributes = attributes;

    }

    public Map<String, Object> getAttributes() {
        return attributes; // 수정
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> owner.getRoles().getKey());

        return authorities;
    }

    @Override
    public String getPassword() {
        return owner.getPassword();
    }

    @Override
    public String getUsername() {
        return owner.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
