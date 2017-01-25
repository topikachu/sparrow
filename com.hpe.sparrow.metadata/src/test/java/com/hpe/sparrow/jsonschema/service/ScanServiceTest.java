package com.hpe.sparrow.jsonschema.service;

import com.hpe.sparrow.jsonschema.service.dummyentity.AnnotationFromParent;
import com.hpe.sparrow.jsonschema.service.dummyentity.ClassHasAnnotation;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by gongy on 2017/1/24.
 */
public class ScanServiceTest {
    @Test
    public void scanEntities() throws Exception {
        ScanService scanService = new ScanService();
        List<Class> entityClasses = scanService.scanEntitiesSubclassOf("com.hpe.sparrow.jsonschema.service.dummyentity");
        assertThat(entityClasses).hasSize(2).contains(AnnotationFromParent.class, ClassHasAnnotation.class);

    }

}