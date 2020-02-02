package org.evue.alistar.api;

import org.evue.alistar.dao.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * ApiApplication
 *
 * @author enilu
 * @version 2018/9/11 0011
 */
@EnableCaching
@SpringBootApplication
@ComponentScan(basePackages = "org.evue.alistar")
@EntityScan(basePackages = "org.evue.alistar.bean.entity")
@EnableJpaRepositories(basePackages = "org.evue.alistar.dao", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableJpaAuditing
public class ApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiApplication.class);
    }
}
