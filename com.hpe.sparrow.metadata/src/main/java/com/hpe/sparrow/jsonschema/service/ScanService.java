package com.hpe.sparrow.jsonschema.service;

import com.google.common.reflect.ClassPath;
import com.hpe.sparrow.ootb.annotation.Entity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by gongy on 2017/1/24.
 */

@Component
public class ScanService {

    public List<Class> scanEntitiesSubclassOf(String basePackage) {
        try {
            return ClassPath.from(ScanService.class.getClassLoader()).getTopLevelClasses(basePackage)
                    .stream()
                    .map(classInfo -> classInfo.load())
                    .filter(clazz -> clazz.isAnnotationPresent(Entity.class) && !Modifier.isAbstract(clazz.getModifiers()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.EMPTY_LIST;
        }
    }
}
