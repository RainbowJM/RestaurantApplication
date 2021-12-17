package com.restaurant.UserService.config;

import com.restaurant.UserService.adapter.ingoing.rest.LoginRestController;
import com.restaurant.UserService.core.domain.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    @Autowired
    private Environment environment;

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // todo: check what the best way is to handle cross-service calls in terms of authentication
        if (request.getRemoteAddr().equals("localhost")) { // "0:0:0:0:0:0:0:1"
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("OtherService", null, Arrays.asList(new SimpleGrantedAuthority("ROLE_"+UserRole.OtherService)));
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.toUpperCase().startsWith("BEARER ")) {
            chain.doFilter(request, response);
            return;
        }

        String authTokenString = authHeader.substring("Bearer ".length()).trim();
        UsernamePasswordAuthenticationToken authToken;
        try {
            authToken = parseAuthToken(authTokenString);
        }
        catch (SignatureException exception) {
            chain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken parseAuthToken(String authToken) throws SignatureException {
        JwtParser parser = Jwts.parser().setSigningKey(LoginRestController.key);
        Claims claims = parser.parseClaimsJws(authToken).getBody();
        String user = claims.getSubject();
        String role = (String)claims.get("role");
        return new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_"+role)));
    }
}
