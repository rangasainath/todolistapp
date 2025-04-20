package com.Application.todolistapp.Config;

import com.Application.todolistapp.Filters.FirstFilter;
import com.Application.todolistapp.Filters.secondFilter;
import com.Application.todolistapp.Interceptor.MyCustomInterceptor1;
import com.Application.todolistapp.Interceptor.MyCustomInterceptor2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    MyCustomInterceptor1 mycustominterceptor1;
    @Autowired
    MyCustomInterceptor2 mycustominterceptor2;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("Before interceptors");
        registry.addInterceptor(mycustominterceptor1).addPathPatterns("/api/v1/**");
        registry.addInterceptor(mycustominterceptor2).addPathPatterns("/api/v1/**");
        System.out.println("After interceptor");

    }

//     @Bean
//    public FilterRegistrationBean<FirstFilter> firstFilter(){
//        FilterRegistrationBean<FirstFilter> filterregistrationbean = new FilterRegistrationBean<>();
//        filterregistrationbean.setFilter(new FirstFilter());
//        filterregistrationbean.addUrlPatterns("/api/v1/*");
//        return filterregistrationbean;
//     }
//
//    @Bean
//    public FilterRegistrationBean<secondFilter> secondFilter(){
//        FilterRegistrationBean<secondFilter> filterregistrationbean = new FilterRegistrationBean<>();
//        filterregistrationbean.setFilter(new secondFilter());
//        filterregistrationbean.addUrlPatterns("/api/v1/*");
//        return filterregistrationbean;
//    }


}
