package com.hpe.sparrow.ootb.entity;

import com.hpe.sparrow.ootb.annotation.Entity;

/**
 * Created by gongy on 2017/1/24.
 */
@Entity
abstract public class BaseEntity {
    protected String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
