package finki.mk.uiktBackend.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import finki.mk.uiktBackend.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserService userService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtAuthConstants.HEADER_STRING);
        if (header == null || !header.startsWith(JwtAuthConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getHeader(JwtAuthConstants.HEADER_STRING);
        if (token != null) {

            String email = JWT.require(Algorithm.HMAC512(JwtAuthConstants.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(JwtAuthConstants.TOKEN_PREFIX, ""))
                    .getSubject();
            ArrayNode claims = JWT.require(Algorithm.HMAC512(JwtAuthConstants.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(JwtAuthConstants.TOKEN_PREFIX, ""))
                    .getClaim(JwtAuthConstants.CLAIM_AUTHORITY).as(ArrayNode.class);

            List<GrantedAuthority> authorities = new ArrayList<>();
            claims.elements().forEachRemaining(claim -> authorities.add(new SimpleGrantedAuthority(claim.asText())));

            if (email != null) {
                var user = userService.findUserByEmail(email);
                return new UsernamePasswordAuthenticationToken(email, null, authorities);
            }
            return null;
        }
        return null;
    }
}
