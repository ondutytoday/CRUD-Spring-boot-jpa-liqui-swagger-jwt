package org.vasileva.crud;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ToJsonMapper {

    public static String toJson(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}