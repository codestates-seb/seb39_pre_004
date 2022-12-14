package team.pre004.stackoverflowclone.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.common.CMRespDto;
import team.pre004.stackoverflowclone.dto.common.QuestionRespDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.ResponseCode;
import team.pre004.stackoverflowclone.handler.exception.CustomNotAccessItemsException;
import team.pre004.stackoverflowclone.web.config.auth.Jwt;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper om = new ObjectMapper();
            Users user = om.readValue(request.getInputStream(), Users.class);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            return authentication;

        } catch (IOException e) {
            throw new CustomNotAccessItemsException(ExceptionMessage.NOT_ACCESS_AUTHORIZATION);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        System.out.println("successfulAuthentication");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject("pre004")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000 * 10)))
                .withClaim("ownerId", principalDetails.getOwner().getOwnerId())
                .withClaim("email", principalDetails.getOwner().getEmail())
                .sign(Algorithm.HMAC512(Jwt.SECRET_CODE.getValue()));
        response.addHeader("Authorization", "Bearer " + jwtToken);
    }
}