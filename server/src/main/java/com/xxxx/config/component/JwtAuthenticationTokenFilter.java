package com.xxxx.config.component;

import com.xxxx.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${tokenHead}")
    private String tokenHead;

    @Value("${tokenHeader}")
    private String tokenHeader;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenful = request.getHeader(tokenHeader);
        if (tokenful!=null&&tokenful.startsWith(tokenHead)) {
            String token = tokenful.substring(tokenHead.length());
            String username = jwtTokenUtil.getUserNameFromToken(token);
            if (username!=null&& SecurityContextHolder.getContext().getAuthentication()==null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(token,userDetails)) {
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities()));
                }
            }
            filterChain.doFilter(request,response);
        }
    }
}
