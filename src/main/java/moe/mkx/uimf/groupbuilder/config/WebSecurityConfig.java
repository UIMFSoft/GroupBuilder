package moe.mkx.uimf.groupbuilder.config;

import org.hibernate.boot.model.relational.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired DataSource dataSource;

//    @Bean(name="passwordEncoder")
//    public PasswordEncoder passwordencoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/api/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
    // WE NEED TO WIRE IN A DATABASE CONNECTION.
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        authN.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                "select username, password,1  from public.\"LOGIN_USER\" " +
                        "where username=?")
                .authoritiesByUsernameQuery(
                        "select username, authority from public.\"LOGIN_USER\" " +
                                "where username=?")
        .passwordEncoder(new BCryptPasswordEncoder());

    }

}