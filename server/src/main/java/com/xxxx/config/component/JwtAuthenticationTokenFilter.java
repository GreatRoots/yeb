package com.xxxx.config.component;

import com.xxxx.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Jwt登录授权过滤器
 *
 * @author zhoubin
 * @since 1.0.0
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	@Resource
	private UserDetailsService userDetailsService;
	@Resource
	private JwtTokenUtil jwtTokenUtil;
	@Value("${jwt.tokenHeader}")
	private String tokenHeader;
	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		String header = request.getHeader(tokenHeader);
		if (header!=null&&header.startsWith(tokenHead)) {
			String token = header.substring(tokenHead.length());
			String username = jwtTokenUtil.getUserNameFromToken(token);
			if (username!=null&& SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				if (jwtTokenUtil.validateToken(token,userDetails)) {
					SecurityContextHolder
							.getContext()
							.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities()));
				}
			}
			chain.doFilter(request,response);
		}
	}
}