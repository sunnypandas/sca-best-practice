package com.acgnfuns.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import javax.xml.bind.ValidationException;
import java.io.File;
import java.io.IOException;

public class JsonUtils {
    private static final String JSON_FILE_DIR = "json/";
    private static final String SCHEMA_FILE_DIR = "schema/";
    private static final String JSON_FILE_EXT = ".json";
    public static Object json2Object(String jsonFileName, Class targetClass) {
        try {

            ClassLoader classLoader = JsonUtils.class.getClassLoader();
            File jsonFile = new File(classLoader.getResource(JSON_FILE_DIR + jsonFileName + JSON_FILE_EXT).getFile());
            File schemaFile = new File(classLoader.getResource(SCHEMA_FILE_DIR +
                    jsonFileName + JSON_FILE_EXT).getFile());
            final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            final JsonSchema jsonSchema = factory.getJsonSchema(schemaFile.toURI
                    ().toString());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(jsonFile);
            ProcessingReport processingReport = jsonSchema.validate(json);
            if (!processingReport.isSuccess()) {
                throw new ValidationException(processingReport.toString());
            }

            return mapper.readValue(jsonFile, targetClass);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcessingException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
