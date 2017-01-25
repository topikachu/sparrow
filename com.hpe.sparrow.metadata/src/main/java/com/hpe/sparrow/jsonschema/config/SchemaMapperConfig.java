package com.hpe.sparrow.jsonschema.config;

import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.zientarski.MapperContext;
import pl.zientarski.PropertyDiscoveryMode;
import pl.zientarski.SchemaMapper;
import pl.zientarski.TypeDescription;
import pl.zientarski.typehandler.DefaultTypeHandler;

/**
 * Created by gongy on 2017/1/25.
 */
@Configuration
public class SchemaMapperConfig {
    @Bean
    public SchemaMapper getSchemaMapper() {
        SchemaMapper schemaMapper = new SchemaMapper();
        schemaMapper.setPropertyDiscoveryMode(PropertyDiscoveryMode.GETTER);
        schemaMapper.addTypeHandler(new DefaultTypeHandler() {
            @Override
            public JSONObject process(TypeDescription typeDescription, MapperContext mapperContext) {
                return super.process(typeDescription, mapperContext);
            }
        });
        return schemaMapper;
    }


}
