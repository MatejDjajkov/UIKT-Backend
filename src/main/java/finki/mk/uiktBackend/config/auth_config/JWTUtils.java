package finki.mk.uiktBackend.config.auth_config;

import com.auth0.jwt.JWT;
import finki.mk.uiktBackend.model.auth.UserInApp;
import finki.mk.uiktBackend.model.exceptions.PasswordDoNotMatchException;
import finki.mk.uiktBackend.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JWTUtils {
    private final UserService userService;

    public JWTUtils(UserService userService) {
        this.userService = userService;
    }

    public List<GrantedAuthority> addAuthoritiesFromRoles(UserInApp user, String password) {
        List<GrantedAuthority> authorities = new ArrayList<>();
            if (!userService.passwordMatches(user, password)) {
                throw new PasswordDoNotMatchException();
            }
            for (var role : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getRole().getName()));
            }

        return authorities;
    }

    public void createAndAppendToken(UserDetails userDetails, HttpServletResponse res) throws IOException {
        String token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtAuthConstants.EXPIRATION_TIME))
                .withArrayClaim(JwtAuthConstants.CLAIM_AUTHORITY, (userDetails).getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .sign(HMAC512(JwtAuthConstants.SECRET.getBytes()));
        res.addHeader(JwtAuthConstants.HEADER_STRING, JwtAuthConstants.TOKEN_PREFIX + token);
        res.getWriter().append(token);
    }

    public void createAndAppendToken(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
        UserDetails userDetails = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        createAndAppendToken(userDetails, res);
    }
}
