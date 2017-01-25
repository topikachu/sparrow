package com.hpe.sparrow.jsonschema.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by gongy on 2017/1/25.
 */

//{"$schema":"http://json-schema.org/draft-04/schema#","additionalProperties":false,"type":"object","properties":{"s3":{"type":"string"},"class2":{"$ref":"Class2"},"i1":{"$ref":"int"},"i3":{"$ref":"int"},"s1":{"type":"string"}},"required":["i3"]}
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonSchema {
    @JsonProperty("$schema")
    private String schema;
    private boolean additionalProperties;
    private String type;
    private Map<String, Property> properties;
    private List<String> required;

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public boolean isAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(boolean additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Property> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Property> properties) {
        this.properties = properties;
    }

    public List<String> getRequired() {
        return required;
    }

    public void setRequired(List<String> required) {
        this.required = required;
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Property {
        private String type;
        @JsonProperty("$ref")
        private String ref;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }
    }
}
