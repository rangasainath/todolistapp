package com.Application.todolistapp.Filters;

import jakarta.servlet.*;

import java.io.IOException;


public class FirstFilter implements Filter {

   @Override
   public void init(FilterConfig filterconfig) throws ServletException
   {
    Filter.super.init(filterconfig);
   }


   @Override
   public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)throws IOException, ServletException {
       System.out.println("this is fromdofilter");
       filterchain.doFilter(servletrequest, servletresponse);
       System.out.println("MyFilter1 completed");

   }


    @Override
   public void destroy(){
       Filter.super.destroy();
   }


}
