package com.Application.todolistapp.Filters;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

public class secondFilter implements Filter {

    @Override
    public void init(FilterConfig filterconfig)throws ServletException {
        Filter.super.init(filterconfig);
    }


    @Override
    public void doFilter(ServletRequest servletrequest, ServletResponse servlerresponse,FilterChain filterchain)throws IOException, ServletException {
        System.out.println("this is from Filter2 BeforeStart of filterchain");
        filterchain.doFilter(servletrequest,servlerresponse);
        System.out.println("this is from Filter2 afterCompletion");
    }


    @Override
    public void destroy(){
        Filter.super.destroy();
    }
}
