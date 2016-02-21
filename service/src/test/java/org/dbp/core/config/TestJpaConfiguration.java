package org.dbp.core.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TestJpaConfiguration {

	@Bean
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		dataSource.setUrl("jdbc:hsqldb:mem:pruebas");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
	    return dataSource;
	}
	
	/**
	 * Configuramos el entity manager en JPA. - Le indicamos el data source con
	 * el que va a trabajar. - Le indicamos el paquete donde se encuentran las
	 * clases. - Le pasamos el adaptador de JPA que en nuestro caso sera
	 * hibernate. - Por otro lado le pasamos las propiedades.
	 * 
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(getDataSource());
		em.setPackagesToScan(new String[] { "org.dbp.bom" });
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(propiedadesAdicionalesJpa());
		return em;
	}
	
	/**
	 * Le indicamos el tipo de configuraci�n que nos interesa para hibernate -
	 * Le indicmaos que cada vez que entremos borre y cree las B.D. - Le
	 * indicamos que utilice el dialecto con HSQL.
	 * 
	 * Nota: este m�todo no es parte de la configuraci�n de spring.
	 * 
	 * @return
	 */
	private Properties propiedadesAdicionalesJpa() {
		return new Properties() {
			{
				setProperty("hibernate.hbm2ddl.auto", "create");
				setProperty("hibernate.jdbc.batch_size", "20");
				setProperty("hibernate.show_sql", "true");
				setProperty("hibernate.dialect","org.hibernate.dialect.HSQLDialect");
				setProperty("hibernate.hbm2ddl.import_files","/META-INF/inicializar.sql");
			}
		};
	}

	/**
	 * Configura las transaciones en JPA.
	 * 
	 * @return
	 */
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){ 
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
	
}
