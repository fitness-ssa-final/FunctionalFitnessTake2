package gov.ssa.functionalfitness.config;

import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import gov.ssa.functionalfitness.entity.User;

import org.apache.commons.dbcp.BasicDataSource;

@Configuration
@EnableTransactionManagement
public class DbConfig {

	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(sessionFactory());
	}

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/functional_fitness?autoReconnect=true&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("sesame");
		return dataSource;
	}

	@Bean
	public SessionFactory sessionFactory() {
		return new LocalSessionFactoryBuilder(getDataSource()).addAnnotatedClasses(User.class).buildSessionFactory();
	}

	@Bean
	public HibernateTransactionManager hibTransMan() {
		return new HibernateTransactionManager(sessionFactory());
	}

}
