package team.pre004.stackoverflowclone.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Users userEntity = usersRepository.findByName(name);
        return new PrincipalDetails(userEntity);
    }

}