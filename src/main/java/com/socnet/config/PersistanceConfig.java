package com.socnet.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.socnet.persistance.repository")
public class PersistanceConfig {

	@Autowired
	MessageSource messageSource;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			JpaVendorAdapter jpaVendorAdapter){
		
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan("com.socnet.persistance.entities");
		
	
		return factoryBean;
	}
	
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory factory){
		
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(factory);
		return tm;
	}
	
	@Bean
	DataSource dataSource(){
		org.apache.tomcat.jdbc.pool.DataSource dataSource = 
					new org.apache.tomcat.jdbc.pool.DataSource();
		
		System.out.println("in dataSource():");
		System.out.println(messageSource.getMessage("db.driver", null, null));
		
		dataSource.setDriverClassName(messageSource.getMessage("db.driver", null, null));
		dataSource.setUrl(messageSource.getMessage("db.url", null, null));
		dataSource.setUsername(messageSource.getMessage("db.username", null, null));
		dataSource.setPassword(messageSource.getMessage("db.password", null, null));
		return dataSource;
}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
		
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform(messageSource.getMessage("hibernate.dialect", null, null));
		return adapter;
	}	
	

}