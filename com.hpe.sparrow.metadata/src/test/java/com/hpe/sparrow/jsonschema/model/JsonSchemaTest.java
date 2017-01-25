package com.hpe.sparrow.jsonschema.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.hpe.sparrow.jsonschema.config.ObjectMapperConfig;
import com.hpe.sparrow.spring.DisableAutowireRequireInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by gongy on 2017/1/25.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ObjectMapperConfig.class},
        initializers = DisableAutowireRequireInitializer.class)
public class JsonSchemaTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testDeserialize() throws IOException {
        URL resource = JsonSchemaTest.class.getResource("class1.schema.json");
        JsonSchema jsonSchema = objectMapper.readValue(Resources.toString(resource, StandardCharsets.UTF_8), JsonSchema.class);
        assertThat(jsonSchema.getSchema()).isEqualTo("http://json-schema.org/draft-04/schema#");
        assertThat(jsonSchema.getRequired()).hasSize(1).contains("i3");
        assertThat(jsonSchema.getProperties())
                .hasSize(5)
                .containsKeys("i1", "s1", "i3", "s3", "class2");
        assertThat(jsonSchema.getProperties().get("i1").getType()).isEqualTo("integer");
        assertThat(jsonSchema.getProperties().get("i1").getRef()).isNull();
        assertThat(jsonSchema.getProperties().get("class2").getType()).isNull();
        ;
        assertThat(jsonSchema.getProperties().get("class2").getRef()).isEqualTo("Class2");

    }

}
