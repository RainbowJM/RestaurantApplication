package com.restaurant.RestaurantService.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Arrays;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    private String privateToken;
    private String signingKey;

    public AuthorizationFilter(AuthenticationManager authenticationManager, String privateToken, String signingKey) {
        super(authenticationManager);
        this.privateToken = privateToken;
        this.signingKey = signingKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.toUpperCase().startsWith("BEARER ")) {
            chain.doFilter(request, response);
            return;
        }

        String authTokenString = authHeader.substring("Bearer ".length()).trim();
        UsernamePasswordAuthenticationToken authToken;
        try {
            authToken = parseAuthToken(authTokenString);
        } catch (SignatureException exception) {
            chain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken parseAuthToken(String authToken) throws SignatureException {
        // Check if token matches the private token, in which case the request was sent by another service and gets its own role granted
        if (privateToken.equals(authToken)) {
            return new UsernamePasswordAuthenticationToken("OtherService", null, Arrays.asList(new SimpleGrantedAuthority("ROLE_OtherService")));
        }

        // Parse token if it's not the private shared token
        JwtParser parser = Jwts.parser().setSigningKey(signingKey);
        Claims claims = parser.parseClaimsJws(authToken).getBody();
        String user = claims.getSubject();
        String role = (String)claims.get("role");
        return new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_"+role)));
    }
}
