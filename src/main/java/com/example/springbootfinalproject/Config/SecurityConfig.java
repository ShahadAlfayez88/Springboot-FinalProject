package com.example.springbootfinalproject.Config;

import com.example.springbootfinalproject.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/v1/user/login",
                        "/api/v1/user/customer/register",
                        "/api/v1/user/serviceProvider/register",
                        "/api/v1/customer/getComment/**",
                        "/api/v1/customer/getCompany/**",
                        "/api/v1/service/get-all",
                        "/api/v1/service/get-by-category/**",
                        "/api/v1/service/get-by-name/**"
                         ).permitAll()
                .requestMatchers( "/api/v1/address/provider/add",
                        "/api/v1/address/provider/get-all",
                        "/api/v1/address/provider/update/**",
                        "/api/v1/address/provider/delete/**",
                        "/api/v1/book-service/changeStatus/**",
                        "/api/v1/comment/get-all",
                        "/api/v1/user/updateProvider",
                        "/api/v1/provider/get-all",
                        "/api/v1/provider/getOrder/**",
                        "/api/v1/service/provider/get-all",
                        "/api/v1/service/add",
                        "/api/v1/service/update/**",
                        "/api/v1/service/delete/**").hasAuthority("Provider")
                .requestMatchers( "/api/v1/customer/get-all-orders",
                        "/api/v1/customer/getOrder/**",
                        "/api/v1/address/customer/add",
                        "/api/v1/address/customer/get-all",
                        "/api/v1/address/customer/update/**",
                        "/api/v1/address/customer/delete/**",
                        "/api/v1/book-service/book/**",
                        "/api/v1/comment/add/**",
                        "/api/v1/user/updateCustomer").hasAuthority("Customer")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/user/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }



//
// Customer{
//
// "/api/v1/customer/get-all-orders",
// "/api/v1/customer/getOrder/**",
//
//
//
// "/api/v1/address/customer/add"
// "/api/v1/address/customer/get-all",
// "/api/v1/address/customer/update/**",
// "/api/v1/address/customer/delete/**",
// "/api/v1/book-service/book/**",
// "/api/v1/comment/add/**",
// "/api/v1/user/updateCustomer",
// }
//
//// Provider {
// "/api/v1/address/provider/add"
// "/api/v1/address/provider/get-all",
// "/api/v1/address/provider/update/**",
// "/api/v1/address/provider/delete/**",
// "/api/v1/book-service/changeStatus/**",
// "/api/v1/comment/get-all",
// "/api/v1/user/updateProvider",
// "/api/v1/provider/get-all",
// "/api/v1/provider/getOrder/**",
// "/api/v1/service/provider/get-all",
// "/api/v1/service/add",
// "/api/v1/service/update/**",
// "/api/v1/service/delete/**",
//
// }
}
