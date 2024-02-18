package com.unicam.risorseLocali.Security.Jwt.Filter;

import com.unicam.risorseLocali.Core.Service.UserService;
import com.unicam.risorseLocali.Security.Jwt.JwtTokenCreator;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Questa classe ha il compito di fare fa
 * filtro interno per l'autenticazione.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userDetailService;
    @Autowired
    private JwtTokenCreator jwtTokenCreator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.jwtTokenCreator.extractTokenFromRequest(request);

        if (token != null && this.jwtTokenCreator.validate(token)) {
            UserDetails userDetails = this.userDetailService.loadUserByUsername(this.jwtTokenCreator.extractUsername(token));
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails,
                            "", userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

}