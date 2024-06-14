package org.relaxcg.web.config;

import org.relaxcg.web.filter.GzipFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author relaxcg
 * @date 2024/1/12 17:53
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<GzipFilter> gzipFilterFilterRegistrationBean() {
        FilterRegistrationBean<GzipFilter> frb = new FilterRegistrationBean<>();
        frb.setFilter(new GzipFilter());
        frb.addUrlPatterns("/*");
        return frb;
    }

}
