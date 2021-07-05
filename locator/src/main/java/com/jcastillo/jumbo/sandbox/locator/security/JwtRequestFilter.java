package com.jcastillo.jumbo.sandbox.locator.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Validate that every request has its valid token
 * @author jorge castillo
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private DefaultUserDetailService dfs;

    @Autowired
    private JwsUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String username= null;
        String jwt = null;

        if(authHeader!=null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            username = jwtUtil.extractUserName(jwt);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails  = this.dfs.loadUserByUsername(username);
            if(jwtUtil.validateToken(jwt, userDetails)) {
                var usrNameAuthToken= new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                usrNameAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usrNameAuthToken);


            }

        }

        filterChain.doFilter(request, response);



    }


}
