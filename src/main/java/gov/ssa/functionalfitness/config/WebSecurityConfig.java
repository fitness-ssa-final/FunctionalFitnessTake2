package gov.ssa.functionalfitness.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// The paths of the allowed pages.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/index", "/functionalfitness", "/user", "/css/**", "/js/**", "/img/**").permitAll()
				.anyRequest().authenticated().and().formLogin().loginPage("/login_signup").permitAll().and().logout()
				.permitAll();
		http.csrf().disable();
	}

	// Hard coded usernames and passwords
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		auth.inMemoryAuthentication().withUser("test").password("123").roles("USER");
		auth.inMemoryAuthentication().withUser("vafa").password("vafa").roles("USER");
		auth.inMemoryAuthentication().withUser("paul").password("paul").roles("USER");
		auth.inMemoryAuthentication().withUser("brendan").password("brendan").roles("USER");
		auth.inMemoryAuthentication().withUser("jamal").password("jamal").roles("USER");
	}
}
