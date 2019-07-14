package ua.testing.demo_jpa.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // In this example the locale information lies on the parameter of the URL.
    // Locale information will stored in Cookie, and the user does not reselect language in the next pages.
    // URL example: http://localhost:8080/SomeContextPath/login1?lang=en
	
    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver() {
//        CookieLocaleResolver resolver = new CookieLocaleResolver();
//        resolver.setCookieDomain("myAppLocaleCookie");
//        resolver.setCookieMaxAge(60 * 60); // 60 minutes
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        return resolver;
    }

    @Bean(name = "messageSource")
    public MessageSource getMessageResource() {
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();

        // Read i18n/messages_xxx.properties file.
        // For example: i18n/messages_en.properties
        messageResource.setBasename("classpath:i18n/messages");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang");

        registry.addInterceptor(localeInterceptor).addPathPatterns("/*");
    }

/*

    // URL example: http://localhost:8080/SomeContextPath/en/login2:

    @Bean(name = "messageSource")
    public MessageSource getMessageResource() {
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();

        // Read i18n/messages_xxx.properties file.
        // For example: i18n/messages_en.properties
        messageResource.setBasename("classpath:i18n/messages");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }

    // To solver URL like:
    // /SomeContextPath/en/login2
    // /SomeContextPath/vi/login2
    // /SomeContextPath/fr/login2
    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver() {
        LocaleResolver resolver = new UrlLocaleResolver();
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        UrlLocaleInterceptor localeInterceptor = new UrlLocaleInterceptor();

        registry.addInterceptor(localeInterceptor).addPathPatterns("/en/*", "/fr/*", "/vi/*");
    }
*/
}