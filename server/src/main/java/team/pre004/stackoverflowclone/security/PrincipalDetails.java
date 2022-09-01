package team.pre004.stackoverflowclone.security;

import lombok.*;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import team.pre004.stackoverflowclone.domain.user.entity.Users;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User  {

    private static final long serialVersionUID = 1L;
    private Users owner;
    private Map<String, Object> attributes;

    public PrincipalDetails(Users owner) {
        this.owner = owner;
    }
    public PrincipalDetails(Users owner, Map<String, Object> attributes) {
        this.owner = owner;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> owner.getRoles().getKey());
//        owner.getRoleList().forEach(n -> {
//            authorities.add(() -> n);
//        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return owner.getPassword();
    }

    @Override
    public String getUsername() {
        return owner.getEmail();
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

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }


}
