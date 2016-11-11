package com.socnet.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

import sun.dc.path.PathError;

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
	MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames(PERSISTENCE_SOURCE_BASE_NAME); 
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

	
	
}