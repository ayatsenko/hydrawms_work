package com.idltd.hydramob.config;

import com.idltd.hydramob.utils.OracleUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Properties;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf()
                .csrfTokenRepository(new HttpSessionCsrfTokenRepository());
        http
        .authorizeRequests()
        .antMatchers("/img/**","/js/**","/spark/**").permitAll()
        .antMatchers("/index","/403","/sessioncheck/**").permitAll()

        .anyRequest().authenticated()
        .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/")
            .permitAll()
        .and()
             .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
            .permitAll()
         .and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
        .and()
            .rememberMe()
            .rememberMeServices(rememberMeServices())
            .key("remember-me-key");
    }

    @Autowired
    private OracleUserDetailsService oracleUserDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenBasedRememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices("remember-me-key", oracleUserDetailsService);
    }
//    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
  //      auth.userDetailsService(oracleUserDetailsService);
        auth.userDetailsService(oracleUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(465);

        mailSender.setUsername("wms.zammler@gmail.com");
        mailSender.setPassword("1Qaz2Wsx3Edc#");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
