package com.flinform.carpark.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
/*
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()  // 允许未认证用户访问登录接口
                .anyRequest().authenticated()           // 其他所有请求都需要认证
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
*/

        return authenticationConfiguration.getAuthenticationManager();
    }
}

