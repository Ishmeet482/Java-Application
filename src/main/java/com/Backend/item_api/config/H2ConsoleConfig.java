package com.Backend.item_api.config;

import jakarta.servlet.Servlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(name = "org.h2.server.web.JakartaWebServlet")
public class H2ConsoleConfig {

    @Bean
    @ConditionalOnProperty(prefix = "spring.h2.console", name = "enabled", havingValue = "true")
    public ServletRegistrationBean<Servlet> h2ConsoleServletRegistration() {
        Servlet servlet = createH2Servlet();
        ServletRegistrationBean<Servlet> registrationBean =
                new ServletRegistrationBean<>(servlet, "/h2-console/*");
        registrationBean.addInitParameter("webAllowOthers", "false");
        registrationBean.addInitParameter("trace", "false");
        return registrationBean;
    }

    private Servlet createH2Servlet() {
        try {
            Class<?> servletClass = Class.forName("org.h2.server.web.JakartaWebServlet");
            return (Servlet) servletClass.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to initialize H2 web console servlet", ex);
        }
    }
}
