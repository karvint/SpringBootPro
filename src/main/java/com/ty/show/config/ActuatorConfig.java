package com.ty.show.config;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Actuator配置类
 * @author zyc
 */
@Configuration
public class ActuatorConfig {
    @ConditionalOnMissingBean(HttpTraceRepository.class)
    @Bean
    public InMemoryHttpTraceRepository traceRepository(){
        InMemoryHttpTraceRepository inMemoryHttpTraceRepository = new InMemoryHttpTraceRepository();
        inMemoryHttpTraceRepository.setCapacity(2);
        return inMemoryHttpTraceRepository;
    }
}
