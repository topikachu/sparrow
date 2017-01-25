package com.hpe.sparrow.jsonschema.config;

import org.springframework.context.annotation.Configuration;

/**
 * Created by gongy on 2017/1/25.
 */
@Configuration
public class AppConfig {
    //@Value("#{entity.ootb.package.name}")
    private String entityOOTBPackageName;

    public String getEntityOOTBPackageName() {
        return entityOOTBPackageName;
    }
}
