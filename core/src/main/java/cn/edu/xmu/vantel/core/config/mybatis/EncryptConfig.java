package cn.edu.xmu.vantel.core.config.mybatis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "vantel.encrypt.enable", havingValue = "true")
public class EncryptConfig {
    @Bean
    public EncryptInterceptor encryptInterceptor() {
        return new EncryptInterceptor();
    }

    @Bean
    public DecryptInterceptor decryptInterceptor() {
        return new DecryptInterceptor();
    }
}
