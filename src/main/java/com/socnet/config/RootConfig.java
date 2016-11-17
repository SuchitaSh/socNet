package com.socnet.config;

import com.socnet.interceptors.LoginRequiredInterceptor;
import com.socnet.interceptors.RestLoginRequiredInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@ComponentScan(basePackages = "com.socnet", excludeFilters =
@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class))
public class RootConfig extends WebMvcConfigurerAdapter {

    private static final String PERSISTENCE_SOURCE_BASE_NAME = "properties/persistence";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }

    @Bean
    MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames(PERSISTENCE_SOURCE_BASE_NAME);
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    public LoginRequiredInterceptor loginRequiredInterceptor() {
        return new LoginRequiredInterceptor();
    }

    @Bean
    public RestLoginRequiredInterceptor restLoginRequiredInterceptor() {
        return new RestLoginRequiredInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginRequiredInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/api/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/register");

        registry.addInterceptor(restLoginRequiredInterceptor())
                .addPathPatterns("/api/**");
    }



//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(rememberMeInterceptor);
//        registry.addInterceptor(initWebContentInterceptor());
//        registry.addInterceptor(loginRequired).excludePathPatterns("/api/account-exist/**")
//                .excludePathPatterns("/api/account-forget/**").addPathPatterns("/api/**").addPathPatterns("/plugins/**")
//                .addPathPatterns("/teams").addPathPatterns("/teams/**").addPathPatterns("/account")
//                .addPathPatterns("/account/profile").addPathPatterns("/account/password").addPathPatterns("/account/sshkey");
//    }


}