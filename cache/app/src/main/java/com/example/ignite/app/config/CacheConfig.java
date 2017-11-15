package com.example.ignite.app.config;

import javax.servlet.ServletContextListener;

import org.apache.ignite.cache.websession.WebSessionFilter;
import org.apache.ignite.startup.servlet.ServletContextListenerStartup;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    public static String IGNITE_CONFIG = "ignite-config.xml";
    public static String CACHE_NAME = "sessionCache";

    @Bean
    public ServletContextListener servletContextListener() {
        return new ServletContextListenerStartup();
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            servletContext.setInitParameter("IgniteConfigurationFilePath", IGNITE_CONFIG);
            servletContext.setInitParameter("IgniteWebSessionsCacheName", CACHE_NAME);
        };
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setName("IgniteWebSessionsFilter");
        filterRegistrationBean.setFilter(new WebSessionFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

}