package gov.ssa.functionalfitness.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import gov.ssa.functionalfitness.entity.User;

@Configuration
@EnableTransactionManagement
public class DbConfig {

	// Making Hibernate template for session
	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(sessionFactory());
	}

	// DB Creds
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/functional_fitness?autoReconnect=true&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("sesame");
		return dataSource;
	}

	// Making Hibernate Session
	@Bean
	public SessionFactory sessionFactory() {
		return new LocalSessionFactoryBuilder(getDataSource()).addAnnotatedClasses(User.class).buildSessionFactory();
	}

	// Setting up hibernate transactions for DB actions
	@Bean
	public HibernateTransactionManager hibTransMan() {
		return new HibernateTransactionManager(sessionFactory());
	}
}
