package ua.testing.demo_jpa.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import ua.testing.demo_jpa.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /* The pages does not require login */
        http.authorizeRequests()
                .antMatchers("/", "/signup").permitAll();

        /* ROLE_USER */
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users/{userId}")
                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

        /* ROLE_ADMIN */
        http.authorizeRequests()
                .antMatchers("/users/*")
                .access("hasRole('ROLE_ADMIN')");

		/*
		 When the user has logged in as XX.
		 But access a page that requires role YY,
		 AccessDeniedException will be thrown.
		*/
        http.authorizeRequests()
                .and().exceptionHandling().accessDeniedPage("/403");

        /* Config for Login Form */
        http.authorizeRequests()
                .and().formLogin()

                // Submit URL of login page.
                .loginPage("/login")

                // Config for Logout Page
                .and().logout();

        /* Config Remember Me. */
        http.authorizeRequests()
                .and()
                .rememberMe().tokenRepository(this.persistentTokenRepository())
                .tokenValiditySeconds(20 * 60); // 20 min
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		/*
		 Setting Service to find User in the database.
		 And Setting PasswordEncoder
		*/
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Token stored in Table (Persistent_Logins)
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
}