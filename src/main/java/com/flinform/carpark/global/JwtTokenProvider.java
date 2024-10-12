package com.flinform.carpark.global;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    // 生成JWT令牌的方法
    public String generateToken(Authentication authentication) {
        // 这里应根据实际需求生成JWT令牌
        // 示例：使用用户名作为载荷的一部分生成令牌
        String username = authentication.getName();
        String token = "Bearer " + username; // 示例生成方式
        return token;
    }
}
