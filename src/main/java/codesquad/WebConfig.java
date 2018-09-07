package codesquad;

import codesquad.security.BasicAuthInterceptor;
import codesquad.security.CartSessionHandlerMethodArgumentResolver;
import codesquad.security.FixedPasswordEncoder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new FixedPasswordEncoder();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheMillis(30);
        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor() {
        return new MessageSourceAccessor(messageSource());
    }

    @Bean
    public BasicAuthInterceptor basicAuthInterceptor() {
        return new BasicAuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(basicAuthInterceptor());
    }

    @Bean
    public CartSessionHandlerMethodArgumentResolver sessionCartArgumentResolver() {
        return new CartSessionHandlerMethodArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(sessionCartArgumentResolver());
    }
}
