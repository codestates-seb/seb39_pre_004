package team.pre004.stackoverflowclone.web.config.auth;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import team.pre004.stackoverflowclone.domain.user.Role;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.exception.CustomDuplicationUsersException;
import team.pre004.stackoverflowclone.handler.exception.CustomNotContentByIdException;
import team.pre004.stackoverflowclone.security.PrincipalDetails;

@Service
@AllArgsConstructor

public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UsersRepository usersRepository;




    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oauth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getClientId();
        String providerId = oauth2User.getAttribute("sub");
        String name = oauth2User.getAttribute("name");
        String email = oauth2User.getAttribute("email");


        usersRepository.findByName(name).ifPresent(
                users -> new CustomDuplicationUsersException(ExceptionMessage.CONFLICT_USER_EMAIL)
        );

        usersRepository.findByEmail(email).ifPresent(
                users -> new CustomDuplicationUsersException(ExceptionMessage.CONFLICT_USER_EMAIL)
        );

        Users userEntity = Users.builder()
                .name(name)
                .email(email)
                .roles(Role.ADMIN)
                .build();

        usersRepository.saveAndFlush(userEntity);


        return new PrincipalDetails(userEntity, oauth2User.getAttributes());
    }
}