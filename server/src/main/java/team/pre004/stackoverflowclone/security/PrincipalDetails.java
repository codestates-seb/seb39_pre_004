package team.pre004.stackoverflowclone.security;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.pre004.stackoverflowclone.domain.user.entity.Users;


import java.util.ArrayList;
import java.util.Collection;

@Data
@Setter(AccessLevel.NONE)
public class PrincipalDetails implements UserDetails {

    private static final long seralVersionUID = 1L;
    private Users users;

    @Builder
    public PrincipalDetails(Users users) {

        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        users.getRoleList().forEach(n -> {
            authorities.add(() -> n);
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getName();
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
