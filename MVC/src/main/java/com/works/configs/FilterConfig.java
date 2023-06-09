package com.works.configs;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        String[] urls = {"/", "/login"};
        boolean loginStatus = true;
        for( String item : urls ) {
            if ( item.equals(url) ) {
                loginStatus = false;
                break;
            }
        }

        if ( loginStatus ) {
            boolean sessionLogin = req.getSession().getAttribute("admin") == null;
            if (sessionLogin) {
                // oturum yok!
                res.sendRedirect("/");
            }else {
                // oturum var!
                filterChain.doFilter(req, res);
            }
        }else {
            filterChain.doFilter(req, res);
        }


    }

}
