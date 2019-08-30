package com.tangu.tangucore.security.jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author fenglei on 8/29/17.
 */
@SuppressWarnings("ALL")
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;
    
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    
    @Override
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException{
        String authToken = request.getHeader(this.tokenHeader);
        if (authToken != null && authToken.startsWith(tokenHead)) {
            authToken = authToken.substring(tokenHead.length()); // The part after "Bearer "
        }
        if(authToken == null){
            authToken = getTokenFromCookie(request);
        }
        JwtUser user = jwtTokenUtil.getJwtUserFromToken(authToken);
        if(user != null && SecurityContextHolder.getContext().getAuthentication() == null){
        	logger.info("checking authentication for user " + user.getUsername());
            if(jwtTokenUtil.validateToken(authToken)){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info("authenticated user "+ user.getUsername() + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request,response);
    }

    private String getTokenFromCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(tokenHeader))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }

}
