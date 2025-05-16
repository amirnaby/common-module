package com.niam.commonservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
@PropertySource("classpath:application-common.properties")
public class MessageConfig implements WebMvcConfigurer {
    @Value("${defaultLocale.language}")
    private String defaultLang;
    @Value("${defaultLocale.country}")
    private String defaultCountry;

    @Bean("messageSource")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("language/messages", "language/messages_common");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultLocale(defaultLocale());
        return messageSource;
    }

    @Bean
    public Locale defaultLocale() {
        return new Locale(defaultLang, defaultCountry);
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}