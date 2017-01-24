package com.hpe.sparrow.ootb.annotation;

import java.lang.annotation.*;

/**
 * Created by gongy on 2017/1/24.
 */
@Target({ElementType.TYPE})
@Inherited

@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
}
