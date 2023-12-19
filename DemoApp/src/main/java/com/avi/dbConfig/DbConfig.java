package com.parivesh.dbConfig;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.parivesh.util.RSAUtil;
import com.google.common.base.Preconditions;

@Configuration
@EnableJpaRepositories(basePackages = "com.parivesh.repository", entityManagerFactoryRef = "postgresEntityManager", transactionManagerRef = "postgresTransactionManager")
public class DbConfig {

	@Autowired
	private Environment env;

	public DbConfig() {
		super();
	}

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean postgresEntityManager() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(postgresDataSource());
		em.setPackagesToScan("com.parivesh.model");

		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		final HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.ddl-auto"));
		properties.put("hibernate.dialect", env.getProperty("postgres.hibernate.dialect"));
		properties.put("org.hibernate.envers.audit_table_suffix", "_aud");;
		properties.put("hibernate.listeners.envers.autoRegister", true);
		properties.put("hibernate.envers.autoRegisterListeners", true);
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Primary
	@Bean
	public DataSource postgresDataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
		dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
		try {
			dataSource.setUsername(
					Preconditions.checkNotNull(RSAUtil.decrypt(env.getProperty("jdbc.user"), RSAUtil.privateKey)));
			dataSource.setPassword(
					Preconditions.checkNotNull(RSAUtil.decrypt(env.getProperty("jdbc.pass"), RSAUtil.privateKey)));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataSource;
	}

	@Primary
	@Bean
	public PlatformTransactionManager postgresTransactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(postgresEntityManager().getObject());
		return transactionManager;
	}
}
