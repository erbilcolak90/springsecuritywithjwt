package com.springsecurity.jwtwithmongo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;


        if(authHeader != null && authHeader.contains("Bearer")){
            token = authHeader.substring(7);
            try{
                username = tokenManager.parseUserIdFromToken(token);


            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if(tokenManager.tokenValidate(token)){
                UsernamePasswordAuthenticationToken upassToken =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(),null,userDetails.getAuthorities());
                upassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upassToken);
            }
        }
        filterChain.doFilter(request,response);
    }



    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.getSessionTimeout();
    }
}