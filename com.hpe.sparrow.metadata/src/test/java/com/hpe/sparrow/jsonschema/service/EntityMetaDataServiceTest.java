package com.hpe.sparrow.jsonschema.service;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hpe.sparrow.jsonschema.config.SchemaMapperConfig;
import com.hpe.sparrow.spring.DisableAutowireRequireInitializer;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.zientarski.PropertyDiscoveryMode;
import pl.zientarski.SchemaMapper;

/**
 * Created by gongy on 2017/1/24.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SchemaMapperConfig.class, EntityMetaDataService.class},
        initializers = DisableAutowireRequireInitializer.class)

public class EntityMetaDataServiceTest {


    @Autowired
    private EntityMetaDataService entityMetaDataService;

    @Test
    public void getJsonSchema() throws Exception {
        JSONObject jsonSchema = entityMetaDataService.jsonSchema(Class1.class);
        System.out.println(jsonSchema.toString());

    }

    @Test
    public void mergeJsonSchema() throws Exception {
        final SchemaMapper schemaMapper = new SchemaMapper();
        schemaMapper.setPropertyDiscoveryMode(PropertyDiscoveryMode.GETTER);
        EntityMetaDataService entityMetaDataService = new EntityMetaDataService();

        JSONObject schema1 = schemaMapper.toJsonSchema4(Class1.class);
        JSONObject schema2 = schemaMapper.toJsonSchema4(Class2.class);
        System.out.println(schema1.toString());
        System.out.println(schema2.toString());
//        String str = objectMapper.writeValueAsString(entityMetaDataService.merge(schema1, schema2));
//        System.out.println(str);
    }

//    @Test
//    public void test mergeEntityWithCustom

    public static class Class1 extends Class3 {
        private Integer i1;
        private String s1;
        private Class2 class2;

        public Integer getI1() {
            return i1;
        }

        public void setI1(int i1) {
            this.i1 = i1;
        }

        public String getS1() {
            return s1;
        }

        public void setS1(String s1) {
            this.s1 = s1;
        }

        @JsonManagedReference
        public Class2 getClass2() {
            return class2;
        }

        public void setClass2(Class2 class2) {
            this.class2 = class2;
        }
    }

    public static class Class2 {
        private int i2;
        private String s2;


        public int getI2() {
            return i2;
        }

        public void setI2(int i2) {
            this.i2 = i2;
        }

        public String getS2() {
            return s2;
        }

        public void setS2(String s2) {
            this.s2 = s2;
        }
    }

    public static class Class3 {
        private int i3;
        private String s3;

        public int getI3() {
            return i3;
        }

        public void setI3(int i3) {
            this.i3 = i3;
        }

        public String getS3() {
            return s3;
        }

        public void setS3(String s3) {
            this.s3 = s3;
        }
    }
}