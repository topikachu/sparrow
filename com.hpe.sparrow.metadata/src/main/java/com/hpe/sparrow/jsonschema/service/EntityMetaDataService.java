package com.hpe.sparrow.jsonschema.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hpe.sparrow.jsonschema.config.AppConfig;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.zientarski.SchemaMapper;

import java.util.List;

/**
 * Created by gongy on 2017/1/24.
 */
@Component
public class EntityMetaDataService {
    @Autowired
    private ScanService scanService;

    @Autowired
    private AppConfig appConfig;

    @Autowired(required = true)
    private SchemaMapper schemaMapper;


    public List getJsonSchema() {
        scanService.scanEntitiesSubclassOf(appConfig.getEntityOOTBPackageName())
                .stream()
                .map(clazz -> jsonSchema(clazz));
        // .map(jsonNode -> objectMapper.readerForUpdating(jsonNode));
        //.map()
        return null;
    }


    public JSONObject jsonSchema(Class clazz) {
        return schemaMapper.toJsonSchema4(clazz);
    }

    public JsonNode merge(JSONObject mainNode, JSONObject updateNode) {
//        Jsonsch
        return null;
//        JSONObject result=new JSONObject(mainNode,JSONObject.getNames(mainNode));
//
//
//
//
//
//        Iterator<String> fieldNames = updateNode.fieldNames();
//
//        while (fieldNames.hasNext()) {
//            String updatedFieldName = fieldNames.next();
//            JsonNode valueToBeUpdated = mainNode.get(updatedFieldName);
//            JsonNode updatedValue = updateNode.get(updatedFieldName);
//
//            // If the node is an @ArrayNode
//            if (valueToBeUpdated != null && valueToBeUpdated.isArray() &&
//                    updatedValue.isArray()) {
//                // running a loop for all elements of the updated ArrayNode
//                for (int i = 0; i < updatedValue.size(); i++) {
//                    JsonNode updatedChildNode = updatedValue.get(i);
//                    // Create a new Node in the node that should be updated, if there was no corresponding node in it
//                    // Use-case - where the updateNode will have a new element in its Array
//
//                        ((ArrayNode) valueToBeUpdated).add(updatedChildNode);
//
//                    // getting reference for the node to be updated
//                    JsonNode childNodeToBeUpdated = valueToBeUpdated.get(i);
//                    merge(childNodeToBeUpdated, updatedChildNode);
//                }
//                // if the Node is an @ObjectNode
//            } else if (valueToBeUpdated != null && valueToBeUpdated.isObject()) {
//                merge(valueToBeUpdated, updatedValue);
//            } else {
//                if (mainNode instanceof ObjectNode) {
//                    ((ObjectNode) mainNode).replace(updatedFieldName, updatedValue);
//                }
//            }
//        }
//        return mainNode;
    }
}
